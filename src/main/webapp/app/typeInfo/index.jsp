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
        <link  rel="stylesheet" href="/app/typeInfo/css_js/index.css" />
    </master:Content>
    <master:Content contentPlaceHolderId="body">
        <div class="row">
            <div class="col-xs-4 col-sm-4 col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-12" style="text-align: right">
                                <button onclick="add_main()" type="button" class="btn btn-success">
                                    <i class="glyphicon glyphicon-plus"></i>新增
                                </button>
                                <button onclick="edit_main()" type="button" class="btn btn-warning">
                                    <i class="glyphicon glyphicon-edit"></i>修改
                                </button>
                                <button onclick="delete_main()" type="button" class="btn btn-danger">
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
                                <button type="button" class="btn btn-success" onclick="add_detial()">
                                    <i class="glyphicon glyphicon-plus"></i>新增
                                </button>
                                <button type="button" class="btn btn-warning" onclick="edit_detial()">
                                    <i class="glyphicon glyphicon-edit"></i>修改
                                </button>
                                <button type="button" class="btn btn-danger" onclick="delete_detial()">
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


        <div class="modal fade" id="my_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            ×
                        </button>
                        <h3 class="modal-title" id="myModalLabel">
                            <span style="font-weight: bold">新增修改一条数据</span>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="typeId" value=""/>
                        <div class="row">
                            <div class="col-md-12 col-xs-12 col-sm-12">
                                <h5>类型名称</h5>
                                <input type="text" class="form-control" name="typeName" placeholder="请填写类型名称"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-xs-12 col-sm-12">
                                <h5>类型编码</h5>
                                <input type="text" class="form-control" name="typeCode" placeholder="请填写类型编码"/>
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
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <div class="modal" id="my_detial_modal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" >
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">
                            ×
                        </button>
                        <h3 class="modal-title">
                            <span style="font-weight: bold">新增修改一条数据</span>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="typeDetailId" value="">
                        <input type="hidden" name="typeId" value="">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <h5>类型标题：</h5>
                                <input type="text" name="detailName" class="form-control" placeholder="请填写类型标题" required="required"/>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <h5>类型编码：</h5>
                                <input type="text" name="detailCode" class="form-control" placeholder="请填写类型编码" required="required"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <h5>类型值</h5>
                                <input type="text" name="detailValue" class="form-control" placeholder="请填写类型值" required="required"/>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <h5>是否生效</h5>
                                <select class="form-control" name="isValid">
                                    <option value="true">生效</option>
                                    <option value="false">失效</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <h5>备注说明</h5>
                                <textarea rows="3" class="form-control" name="comment" placeholder="请填写备注说明"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <i class="glyphicon glyphicon-remove"></i>关闭
                        </button>
                        <button type="button" class="btn btn-primary" onclick="save_detial()">
                            <i class="glyphicon glyphicon-floppy-save"></i>保存
                        </button>
                    </div>
                </div>
            </div>

        </div>




        <script type="text/javascript" src="/app/typeInfo/css_js/index.js"></script>
        <script type="text/javascript" src="/app/typeInfo/css_js/detail.js"></script>

    </master:Content>
</master:ContentPage>






























