var myTable = $("#myTableTest");
var selecteRow;
var loading = false;//控制项目列表频繁点击
var option = {
    id:"#myTableTest",//需要绑定id或class div
    url:"/app/typeInfo/getMainInfo.do",//表格请求的路径
    isPage:false,
    columns:[
        {name:"typeName",title:"类型名称",align:"lefe",width:"50%"},
        {name:'typeCode',title:'类型编码',align:'left'}
    ]//表格列[{field:'name',title:'名称',align:'left',width:80,template:function(){}},{},{
};

$(window).load(function () {

    myTable.ghTable(option);


    //定义表格产生事件
    myTable.on("table.create",function () {
        loading = false;
    });

    myTable.on("table.row.selected",function (event,eventData) {
        selecteRow = eventData.row;
        if(selecteRow != null){
            option_detail.data = {mainId:selecteRow.typeId};
            myDetailTable.ghTable(option_detail);
        }
    });
});

function add_main() {
    $("#my_modal input,#my_modal select,#my_modal textarea").each(function () {
        // var name = $(this).attr("name");
        $(this).val("");
    });
    $('#my_modal').modal("show");
}
function edit_main() {
    if(selecteRow == null){
        alert("请先选中一行数据");
        return;
    }
    $("#my_modal input,#my_modal select #my_modal textarea").each(function () {
        var name = $(this).attr("name");
        $(this).val(selecteRow[name]);
    });

    $('#my_modal').modal("show");
}

function delete_main() {

    if(selectedRow == null){
        $.messager.alert("操作提示","请先选择一行数据","info");
        return;
    }
    $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
        if (data) {
            $.post(
                "/app/typeInfo/deleteMain.do",
                {mainId:selecteRow.typeId},
                function (rs) {
                    if(rs.error == 0){
                        $("#my_modal").modal('hide');
                        loadTable();
                    }
                },
                "json"
            );
        }
    });
}
function save_main() {
    var postData = get_data();
    $.ajax({
        url:"/app/typeInfo/saveMain.do",
        data: postData,
        type:"post",
        async:true,
        dataType:"json",
        success:function (rs) {
            console.log(rs.msg);
            if(rs.error == 0){
                loadTable();
                $('#my_modal').modal('hide');
            }
        }
    });
}

function get_data() {
    var flag = true;
    var postData = {};
    $("#my_modal input,#my_modal select,#my_modal textarea").each(function () {
        $(this).css("border","1px solid #ccc");
        var name = $(this).attr("name");
        if($(this).attr("required") && !$(this).val()){
            flag = false;
            $(this).css("border","1px solid red");
            var text = "请输入"+$(this).prev().text();
            text = text.replace(":","");
            alert(text);
            return false;
        }else {
            postData[name] = $(this).val();
        }

    });
    if(!flag) return;
    return postData;
}

function loadTable() {
    selecteRow = null;
    myTable.ghTable();
}