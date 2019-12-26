<%@ taglib prefix="master" uri="util.masterPage" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/25
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String menuCode = "cost";
    String title = "消费单";
%>


<master:ContentPage>
    <master:Content contentPlaceHolderId="title"><%=title%></master:Content>
    <master:Content contentPlaceHolderId="head">
        <script type="text/javascript">
            EIIS.Common.loadComponent(EIIS.Common.bootstrap.BootstrapTable);
        </script>
        <script type="text/javascript">
            var menuCode = "<%=menuCode%>";
        </script>
        <script type="text/javascript" href="/app/cost/css_js/index.js"></script>
        <link rel="stylesheet" href="/app/cost/css_js/index.css" />
    </master:Content>
    <master:Content contentPlaceHolderId="body">
        <div class="panel panel-default need-nav">
            <div class="panel-body">
                <div class="col-md-12" style="text-align: right">
                    <button type="button" class="btn btn-primary">
                        <i class="glyphicon glyphicon-search"></i>搜索
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
        <!--画表格-->
        <div class="row">
            <div class="col-md-12">
                <!--表格-->
                <div id="myTableTest"></div>
            </div>
        </div>

        <!--模态框（Modal）-->
        <div id="search_form" class="modal">

        </div>

        <!--模态框（Modal）-->
        <div id="my_modal" class="modal">

        </div>

    </master:Content>
</master:ContentPage>







































