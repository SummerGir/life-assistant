package com.controller.core.menuTree;

import com.app.core.memberTree.entity.CoreMenuTreeInfoEntity;
import com.app.core.memberTree.service.CoreMenuTreeInfoService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.context.Context;
import util.dataManage.GenericController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

        }catch (Exception e){
            e.printStackTrace();
        }
        return GenericController.returnFaild(null);
    }
}





































