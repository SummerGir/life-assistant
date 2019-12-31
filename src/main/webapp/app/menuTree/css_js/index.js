var _treeNode = null;
$(window).load(function () {
    initTree();
});
function initTree() {
    $("#member_tree").tree({
        animate:true,//定义当前节点展开折叠时是否显示动画效果
        lines:true,//定义是否显示树线条
        url:"/core/menuTree/getMenuTree.do",//获取远程数据的URL
        onSelect:function (node) {//当点击一个节点时触发
            _treeNode = node;
            edit_main();
        },

        onLoadSuccess:function (node,data) {//当加载成功时触发
            if(_treeNode != null){
                _treeNode = $("#member_tree").tree("find",_treeNode.id);
                if(_treeNode != null){
                    $("#member_tree").tree("select",_treeNode.target);
                    $("#member_tree").tree("expandTo",_treeNode.target);
                }
            }
        },
        onLoadError:function (arguments) {//当加载失败的时候触发，arguments 参数与 jQuery.ajax 的 'error' 函数一样。
            console.log("onLoadError");
            console.log(arguments);
        }
    });
}
function edit_main() {
    if(_treeNode == null){
        $.messager.alert("操作提示","请选择一个菜单节点！","info");
        return;
    }
    $.post(
        "/core/menuTree/getMainOne.do",
        {mainId:_treeNode.id},
        function (rs) {
            if(rs != null){
                $("#my_modal input,#my_modal select,#my_modal textarea").each(function () {
                    var name = $(this).attr("name");
                    $(this).val(rs[name] + "");
                    $(this)[0].removeAttribute("disabled");
                });
                var parent = $("#member_tree").tree("getParent",_treeNode.target);
                if(parent != null){
                    $("#my_modal *[name='parentId']").val(parent.id);
                }
                if(rs.menuId == "root"){
                    $("#my_modal input,#my_modal select,#my_modal textarea").attr("disabled","disabled");
                    $("#right_div .panel-footer").hide();
                }else {
                    $("#right_div .panel-footer").show();
                }
            }else {
                $.message.alert("操作提示","查询失败","info");
            }
        },
        "json"
    );
}

function reset_tree() {
    $("#member_tree").tree("reload");
}
function move_tree(type) {
    if(_treeNode == null){
        $.messager.alert("操作提示","请选择一个菜单节点！","info");
        return;
    }
    $.ajax({
        url:'/core/menuTree/moveTree.do',
        data:{"treeId":_treeNode.id,"type":type},
        type:"post",
        async:true,
        dataType:"json",
        success:function (rs) {
            if(rs.error == 0){
                reset_tree();
            }else {
                $.messager.alert("操作提示",rs.msg,"info");
            }
        }
    });
}

function save_main(){
    var flag = true;
    var postData = {};
    $("#my_modal input,#my_modal select,#my_modal textarea").each(function () {
        var name = $(this).attr("name");
        if($(this).attr("requird") && !$(this).val()){
            $(this).css("border","1px solid red");
            flag = false;
            $.message.alert("操作提示",$(this).prev().text()+"不能为空！","warning");
            return;
        }
        postData[name] = $(this).val();
    });
    if(!flag) return;
    $.ajax({
        url:"/core/menuTree/saveMain.do",
        data:postData,
        async:true,
        type:"post",
        dataType:"json",
        success:function (rs) {
            $.messager.alert("操作提示","保存成功","info");
            if(rs.error == 0){
                reset_tree();
            }
        }
    });
}

function add_main() {
    if(_treeNode == null){
        $.messager.alert("操作提示","请选择一个菜单节点！","info");
        return;
    }
    $("#my_modal input,#my_modal select,#my_modal textarea").each(function () {
       $(this).val("");
        $(this)[0].removeAttribute("disabled");
    });
    $("#my_modal *[name='parentId']").val(_treeNode.id);
    $("#my_modal *[name='isShow']").val("true");
    $("#right_div .panel-footer").show();
}

function delete_main() {
    if(_treeNode == null){
        $.messager.alert("操作提示","请选择一个菜单节点！","info");
        return;
    }
    $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
        if (data) {
             $.post(
                "/core/menuTree/deleteMain.do",
                {"mainId":_treeNode.id},
                function (rs) {
                    $.messager.alert("操作提示",rs.msg,"info");
                    if(rs.error == 0){
                        reset_tree();
                    }
                },
                "json"
            );
        }
    });
}
























