<%@ page import="com.app.typeinfo.entity.TypeSelectEntity" %>
<%@ page import="com.app.typeinfo.service.AppTypeDetailService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.typeinfo.entity.AppTypeDetailEntity" %>
<%@ page import="util.context.Context" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/9
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="master" uri="util.masterPage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String menuCode = "index";
    if(false){
        TypeSelectEntity pse = AppTypeDetailService.getInstance().getTypeSelect(menuCode,"");
        List<AppTypeDetailEntity> doingList = pse.getDoingList();
        request.getRequestDispatcher(doingList.get(0).getDetailValue()).forward(request,response);
        return;
    }

%>

<master:ContentPage>
    <master:Content contentPlaceHolderId="title">首页</master:Content>
    <master:Content contentPlaceHolderId="head">
        <style type="text/css">
            h4{
                color: blue;
            }
        </style>
    </master:Content>
    <master:Content contentPlaceHolderId="body">
        <h4>这里是首页</h4>
    </master:Content>
</master:ContentPage>
