<%@ page import="com.app.core.memberUrl.service.CoreMenuUrlService" %>
<%@ taglib prefix="master" uri="util.masterPage" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/31
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String title = "菜单管理";
    StringBuffer sb = CoreMenuUrlService.getInstance().getMenuUrlOptions(true);
%>
<master:ContentPage>
    <master:Content contentPlaceHolderId="title"><%=title%></master:Content>
    <master:Content contentPlaceHolderId="head">
        <link rel="stylesheet" href="/app/menuTree/css_js/index.css"/>
    </master:Content>
    <master:Content contentPlaceHolderId="body">
        <div class="row">
            <div class="col-xs-12 col-sm-4 col-md-4">
                <div class="panel panel-default" id="left_div">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-sm-12">
                                <button class="btn btn-primary" type="button" onclick="reset_tree()">
                                    <i class="glyphicon glyphicon-refresh"></i>刷新
                                </button>
                                <button class="btn btn-warning" type="button" onclick="move_tree(true)">
                                    <i class="glyphicon glyphicon-arrow-up"></i>上移
                                </button>
                                <button class="btn btn-warning" type="button" onclick="move_tree(false)">
                                    <i class="glyphicon glyphicon-arrow-down"></i>下移
                                </button>

                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <ul id="member_tree" class="easyui-tree"></ul>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-8 col-md-8">
                <div class="panel panel-default" id="right_div">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-sm-12">
                                <button type="button" class="btn btn-success" onclick="add_main()">
                                    <i class="glyphicon glyphicon-plus"></i>新增下级节点
                                </button>
                                <button type="button" class="btn btn-danger" onclick="delete_main()">
                                    <i class="glyphicon glyphicon-trash"></i>删除当前节点
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body" id="my_modal">
                        <input type="hidden" name="parentId">
                        <input type="hidden" name="menuId">

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 my-col">
                                <div class="my-col-left-div">菜单名称：</div>
                                <div class="my-col-right-div">
                                    <input class="form-control" name="title"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 my-col">
                                <div class="my-col-left-div">菜单路径：</div>
                                <div class="my-col-right-div">
                                    <select class="form-control" name="urlId" >
                                        <%=sb.toString()%>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 my-col">
                                <div class="my-col-left-div">菜单图标：</div>
                                <div class="my-col-right-div">
                                    <input class="form-control"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 my-col">
                                <div class="my-col-left-div">是否显示：</div>
                                <div class="my-col-right-div">
                                    <select class="form-control" name="isShow">
                                        <option value="true">显示</option>
                                        <option value="false">隐藏</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="panel-footer">
                        <button type="button" class="btn btn-success" onclick="save_main()">
                            <i class="glyphicon glyphicon-plus"></i>保存
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="/app/menuTree/css_js/index.js"></script>
    </master:Content>
</master:ContentPage>
