package com.controller.typeinfo;

import com.app.typeinfo.service.AppTypeDetailService;
import com.app.typeinfo.service.AppTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.dataManage.GenericController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller("com.controller.typeinfo.AppTypeInfoController")
@RequestMapping("/app/typeInfo")
public class AppTypeInfoController {
    @Autowired
    protected AppTypeInfoService appTypeInfoService;

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

}
