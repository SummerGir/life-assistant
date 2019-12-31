package com.controller.core.menuTree;

import com.app.core.memberTree.entity.CoreMenuTreeInfoEntity;
import com.app.core.memberTree.service.CoreMenuTreeInfoService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.context.Context;
import util.dataManage.GenericController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller("com.controller.core.menuTree.CoreMenuTreeInfoController")
@RequestMapping("/core/menuTree")
public class CoreMenuTreeInfoController {

    @Autowired
    public CoreMenuTreeInfoService menuTreeService;

    //保存当前访问的菜单
    @RequestMapping("setMenuTree")
    @ResponseBody
    public ObjectNode setMenuTree(@RequestParam(defaultValue = "")String menuId){
        CoreMenuTreeInfoEntity entity = menuTreeService.findOne(menuId);
        Context.setMenuTree(entity);
        System.out.println("记录当前访问菜单："+entity.getTitle());
        return GenericController.returnSuccess(null);
    }


    @RequestMapping("getMenuTree")
    @ResponseBody
    public String getMenuTrees()throws Exception{
        List<Map<String,Object>> list = menuTreeService.getMainInfo(null,null);
        List<Map<String,Object>> list1 = menuTreeService.getMenuTree(list);
        return JSONArray.fromObject(list1).toString();
    }

    @RequestMapping("getMainOne")
    @ResponseBody
    public String getMainOne(HttpServletRequest request) throws Exception{
        String mainId = request.getParameter("mainId");
        List<Map<String,Object>> list= menuTreeService.getMainInfo(mainId,null);
        return JSONObject.fromObject(list.get(0)).toString();
    }

    //保存当前访问的菜单
    @RequestMapping("moveTree")
    @ResponseBody
    public ObjectNode moveTree(HttpServletRequest request) throws Exception{
        String treeId = request.getParameter("treeId");
        String type = request.getParameter("type");
        try{
            menuTreeService.moveTree(treeId,Boolean.parseBoolean(type));
            return GenericController.returnSuccess(null);

        }catch (Exception e){
            e.printStackTrace();
        }
        return GenericController.returnFaild(null);
    }

    @RequestMapping("saveMain")
    @ResponseBody
    public ObjectNode saveMain(HttpServletRequest request){
        String parentId = request.getParameter("parentId");
        String menuId = request.getParameter("menuId");
        CoreMenuTreeInfoEntity entity = new CoreMenuTreeInfoEntity();
        List<CoreMenuTreeInfoEntity> list = new ArrayList();
        if(StringUtils.isBlank(menuId)){
            CoreMenuTreeInfoEntity entityP = menuTreeService.findOne(parentId);
            entityP.setType(false);
            entity.setMenuId(UUID.randomUUID().toString());
            entity.setMenuLevel(menuTreeService.getMenuLevelByParLevel(entityP.getOutlineLevel()));
            entity.setOutlineLevel(entityP.getOutlineLevel() + "." + String.valueOf(entity.getMenuLevel()));
            entity.setType(true);
            list.add(entityP);
        }else {
            entity = menuTreeService.findOne(menuId);
        }
        entity.setTitle(request.getParameter("title"));
        entity.setUrlId(request.getParameter("urlId"));
        entity.setIcon(request.getParameter("icon"));
        entity.setIsShow(Boolean.parseBoolean(request.getParameter("isShow")));
        list.add(entity);
        try {
            menuTreeService.save(list);
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
            menuTreeService.delete(mainId);
        }catch (Exception e){
            e.printStackTrace();
            return GenericController.returnFaild(null);
        }

        return GenericController.returnSuccess(null);
    }

}




































