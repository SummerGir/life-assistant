<%@ taglib prefix="master" uri="util.masterPage" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/1/1
  Time: 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String title = "菜单路径";
%>
<master:ContentPage>
    <master:Content contentPlaceHolderId="title"><%=title%></master:Content>
    <master:Content contentPlaceHolderId="head">
        <link rel="stylesheet" href="css_js/index.css"/>
    </master:Content>
    <master:Content contentPlaceHolderId="body">
        <div class="panel panle-default">
            <div class="panle-body">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12" style="text-align: right;padding: 15px 30px ">
                        <button class="btn btn-primary" type="button" onclick="refresh_main()">
                            <i class="glyphicon glyphicon-refresh"></i>刷新
                        </button>
                        <button class="btn btn-primary" type="button" onclick="search_main()">
                            <i class="glyphicon glyphicon-serach"></i>搜索
                        </button>
                        <button class="btn btn-success" type="button" onclick="add_main()">
                            <i class="glyphicon glyphicon-plus"></i>新增
                        </button>
                        <button class="btn btn-warning" type="button" onclick="edit_main()">
                            <i class="glyphicon glyphicon-edit"></i>修改
                        </button>
                        <button class="btn btn-danger" type="button" onclick="delete_main()">
                            <i class="glyphicon glyphicon-trash"></i>删除
                        </button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <div id="myTableTest"> </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="serach_form" class="modal fade" tabindex="-1" aria-hidden="true" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">×</button>
                        <h3 class="modal-title">
                            <span style="font-weight: bold">搜索</span>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12" style="display: flex">
                                <label style="width: 80px;line-height: 36px; ">关键字:</label>
                                <input type="text" class="form-control" name="searchkey" placeholder="请输入关键字"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <i class="glyphicon glyphicon-remove"></i>关闭
                        </button>
                        <button type="button" class="btn btn-primary" onclick="save_detial()">
                            <i class="glyphicon glyphicon-floppy-save"></i>搜索
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="my_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">×</button>
                        <h3 class="modal-title">
                            <span style="font-weight: bold">新增修改一条数据</span>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <h5>菜单名称</h5>
                                <input type="text" class="form-control" name="urlTitle" placeholder="请输入菜单名称"/>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <h5>菜单名编码</h5>
                                <input type="text" class="form-control" name="urlCode" placeholder="请输入菜单名称"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <h5>菜单路径:</h5>
                                <input type="text" class="form-control" name="urlStr" placeholder="请填写菜单路径：">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <i class="glyphicon glyphicon-remove"></i>关闭
                        </button>
                        <button type="button" class="btn btn-primary" onclick="save_main()">
                            <i class="glyphicon glyphicon-floppy-save"></i>保存
                        </button>
                    </div>

                </div>
            </div>
        </div>


        <script type="text/javascript" src="/app/menuUrl/css_js/index.js"></script>


    </master:Content>
</master:ContentPage>
