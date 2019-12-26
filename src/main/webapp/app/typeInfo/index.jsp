<%@ page import="com.app.core.memberTree.service.CoreMenuTreeInfoService" %>
<%@ page import="util.context.Context" %>
<%@ page import="com.app.core.memberTree.entity.CoreMenuTreeInfoEntity" %>
<%@ taglib prefix="master" uri="util.masterPage" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/25
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String menuCode = "type_info";
    CoreMenuTreeInfoEntity menuTree = Context.getMenuTree(menuCode);
    String title = menuTree.getTitle();

%>

<master:ContentPage>
    <master:Content contentPlaceHolderId="title">title</master:Content>
    <master:Content contentPlaceHolderId="head">
        <script type="text/javascript">
            EIIS.Common.loadComponent(EIIS.Common.bootstrap.BootstrapTable);
        </script>

        <link  rel="stylesheet" href="/app/typeInfo/css_js/index.css" />
        <script type="text/javascript" src="/app/typeInfo/css_js/index.js"></script>
    </master:Content>
    <master:Content contentPlaceHolderId="body">
        <div class="row">
            <div class="col-xs-4 col-sm-4 col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-12" style="text-align: right">
                                <button type="button" class="btn btn-success">
                                    <i class="glyphicon glyphicon-plus"></i>新增
                                </button>
                                <button type="button" class="btn btn-warning">
                                    <i class="glyphicon glyphicon-edit"></i>修改
                                </button>
                                <button type="button" class="btn btn-danger">
                                    <i class="glyphicon glyphicon-trash"></i>删除
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div id="myTableTest"></div>
                    </div>
                </div>
            </div>
            <div class="col-xs-8 col-sm-8 col-md-8">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-12" style="text-align: right">
                                <button type="button" class="btn btn-primary">
                                    <i class="glyphicon glyphicon-arrow-up"></i>上移
                                </button>
                                <button type="button" class="btn btn-primary">
                                    <i class="glyphicon glyphicon-arrow-down"></i>下移
                                </button>
                                <button type="button" class="btn btn-success">
                                    <i class="glyphicon glyphicon-plus"></i>新增
                                </button>
                                <button type="button" class="btn btn-warning">
                                    <i class="glyphicon glyphicon-edit"></i>修改
                                </button>
                                <button type="button" class="btn btn-danger">
                                    <i class="glyphicon glyphicon-trash"></i>删除
                                </button>
                            </div>

                        </div>
                    </div>
                    <div class="panel-body">
                        <div id="myDetailTable"></div>
                    </div>
                </div>
            </div>

        </div>


    </master:Content>
</master:ContentPage>






























