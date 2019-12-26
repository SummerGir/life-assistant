<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.core.memberTree.service.CoreMenuTreeInfoService" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.app.core.memberTree.entity.CoreMenuTreeInfoEntity" %>
<%@ page import="util.context.Context" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/9
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="master" uri="util.masterPage" %>
<%

    JSONArray arr = new JSONArray();
    List<Map<String,Object>> list = CoreMenuTreeInfoService.getInstance().getMainInfo(null,"1");
    List<Map<String,Object>> listTree = CoreMenuTreeInfoService.getInstance().getMenuTree(list);
    if(listTree != null && listTree.size()>0){
        listTree = (List<Map<String,Object>>) listTree.get(0).get("children");
    }
    if(listTree != null && listTree.size()>0){
        arr = JSONArray.fromObject(listTree);
    }
    CoreMenuTreeInfoEntity menuTreeInfoEntity = Context.getMenuTree(null);
    String menuTreeId = menuTreeInfoEntity == null?"":menuTreeInfoEntity.getMenuId();//选中的菜单

%>
<html>
<head>
    <!--子页面标题内容-->
    <title>
        <master:ContentPlaceHolder id="title"/>
    </title>

    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <!--js-->
    <script type="text/javascript" src="/public/jquery/jquery.js"></script>
    <script type="text/javascript" src="/public/eiis/eiis.js"></script>

    <script type="text/javascript" src="/public/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/theme/pc/main/css_js/index.js"></script>
    <script type="text/javascript">
        var menuTreeId = "<%=menuTreeId%>";
        var menuList = <%=arr%>;
    </script>

    <!--设置浏览器窗口图标-->
    <link rel="shortcut icon" type="image/x-icon" href="/theme/pc/main/img/logoIco.ico"media="screen" />

    <!--css-->
    <link rel="stylesheet" href="/public/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="/theme/pc/main/css_js/index.css"/>

    <master:ContentPlaceHolder id="head"/>
</head>
<body>
<!--顶部导航栏 开始-->
<nav class="navbar navbar-default navbar-fixed-top main-top-nav" role="navigation">
    <div class="web-top">
        <div class="web-top-left">
            <a class="navbar-brand" style="padding: 0" href="/theme/pc/index.jsp">
                <img src="/theme/pc/main/img/pcLogo2.png" style="width: 90px" alt="好管家">
            </a>
        </div>
        <div class="web-top-center" id="1">

        </div>
        <div class="web-top-right">
            <ul class="nav navbar-right navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-size: 16px;">
                        <span class="esg-font icon-gangwei"><%=Context.getMember()==null?"登录":Context.getMember().getMemeberName().toString()%></span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">我的信息</a></li>
                        <li><a href="#">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- 顶部导航栏 完成 -->
<div class="main-center-div">
    <div class="main-center-nav">
        <div class="main-center">
            <master:ContentPlaceHolder id="body"/>
        </div>

    </div>
</div>




</body>
</html>
