package com.controller.typeinfo;

import com.app.typeinfo.entity.AppTypeDetailEntity;
import com.app.typeinfo.entity.AppTypeInfoEntity;
import com.app.typeinfo.service.AppTypeDetailService;
import com.app.typeinfo.service.AppTypeInfoService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Context;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.dataManage.GenericController;
import util.dataManage.GenericDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller("com.controller.typeinfo.AppTypeInfoController")
@RequestMapping("/app/typeInfo")
public class AppTypeInfoController {
    @Autowired
    protected AppTypeInfoService appTypeInfoService;

    @Autowired
    protected AppTypeDetailService appTypeDetailService;

//    @Autowired
//    protected AppTypeDetailService appTypeDetailService;

    @RequestMapping("getMainInfo")
    @ResponseBody
    public String getMainInfo(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer rows) throws Exception{
        String mainId = request.getParameter("mainId");
        String searchKey = request.getParameter("searchKey");
        List<Map<String,Object>> list = appTypeInfoService.getMainInfo(mainId,searchKey,page,rows);
        int count = appTypeInfoService.getMainCount(mainId,searchKey);
        return GenericController.getTable(list,count,page,rows);
    }


    @RequestMapping("getDetailInfo")
    @ResponseBody
    public String getDetialInfo(HttpServletRequest request,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer rows)throws Exception{
        String mainId = request.getParameter("mainId");
        String searchKey = request.getParameter("searchKey");
        if(StringUtils.isNotBlank(mainId)){
            List<Map<String,Object>> list = appTypeDetailService.getMainInfo(mainId,searchKey,page,rows);
            int count = appTypeDetailService.getMainCount(mainId,searchKey);
            return GenericController.getTable(list,count,page,rows);
        }else {
            return GenericController.getTable(new ArrayList<Map<String, Object>>(),0,page,rows);
        }

    }

    @RequestMapping("saveMain")
    @ResponseBody
    public ObjectNode saveMain(HttpServletRequest request){
        String mainId = request.getParameter("typeId");
        String typeName = request.getParameter("typeName");
        String typeCode = request.getParameter("typeCode");
        AppTypeInfoEntity entity = new AppTypeInfoEntity();
        if(StringUtils.isNotBlank(mainId)){
            entity = appTypeInfoService.findOne(mainId);
        }else {
            entity.setTypeId(UUID.randomUUID().toString());
        }
        entity.setTypeName(typeName);
        entity.setTypeCode(typeCode);
        entity.setSysTime(new Timestamp(new Date().getTime()));
        entity.setMemberId(util.context.Context.getMember().getMemberId());
        try {
            appTypeInfoService.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            return GenericController.returnFaild(null);
        }
        return GenericController.returnSuccess(null);
    }


    @RequestMapping("deleteMain")
    @ResponseBody
    public ObjectNode deleteMain(HttpServletRequest request){
        String mainId = request.getParameter("mainId");
        try {
            appTypeInfoService.delete(mainId);
            appTypeDetailService.deleteByTypeId(mainId);
        }catch (Exception e){
            e.printStackTrace();
            return GenericController.returnFaild(null);
        }

        return GenericController.returnSuccess(null);
    }

    @RequestMapping("saveDetial")
    @ResponseBody
    public  ObjectNode saveDetial(HttpServletRequest request){
        String typeDetailId = request.getParameter("typeDetailId");
        String typeId = request.getParameter("typeId");
        String detailName = request.getParameter("detailName");
        String isValid = request.getParameter("isValid");
        AppTypeDetailEntity entity = new AppTypeDetailEntity();
        if(StringUtils.isBlank(typeDetailId)){
            entity.setTypeDetailId(UUID.randomUUID().toString());
            entity.setTypeId(typeId);
            entity.setDetailLevel(appTypeDetailService.getLevelByMainId(typeId));
        }else {
            entity = appTypeDetailService.findOne(typeDetailId);
        }
        entity.setDetailName(detailName);
        entity.setDetailCode(request.getParameter("detailCode"));
        entity.setDetailValue(request.getParameter("detailValue"));
        entity.setComment(request.getParameter("comment"));
        entity.setValid(Boolean.parseBoolean(isValid));
        try{
            appTypeDetailService.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            return GenericController.returnFaild(null);
        }
        return GenericController.returnSuccess(null);
    }

    @RequestMapping("deleteDetial")
    @ResponseBody
    public ObjectNode deleteDetial(HttpServletRequest request){
        String typeDetailId = request.getParameter("typeDetailId");
        try {
            appTypeDetailService.delete(typeDetailId);
        }catch (Exception e){
            e.printStackTrace();
            return GenericController.returnFaild(null);
        }
        return GenericController.returnSuccess(null);
    }
}





















