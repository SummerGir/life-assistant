var myTable = $("#myTableTest");
var selectedRow;
var loading = false;
var option = {
    id:"#myTableTest",
    url:"/core/menuUrl/getMainInfo.do",
    data:{},
    columns:[
        {name:'urlTitle',title:"标题",align:'left'},
        {name:'urlCode',title:'编码',align:'left'},
        {name:'urlStr',title:"路径",align:'left',width:'20%'},
        {name:'parameter',title:"参数",align:'left'},
        {name:'sysTime',title:"编制日期",align:'center',width:'20%'}
    ]
};

$(window).load(function(){
    myTable.ghTable(option);
    myTable.on("table.created", function() {
        loading = false;
    });
    myTable.on("table.row.selected", function(event,eventData) {
        selectedRow = eventData.row;
    });
});


function add_main() {
    $("#my_modal input,#my_modal select,#my_modal textarea").each(function () {
        $(this).val("");
    });
    $("#my_modal").modal('show');
}
function edit_main() {
    if(selectedRow == null){
        $.messager.alert("操作提示","请先选择一行数据","info");
        return;
    }
    $("#my_modal input,#my_modal select,#my_modal textarea").each(function () {
        var name = $(this).attr("name");
        $(this).val(selectedRow[name]);
    });
    $("#my_modal").modal('show');
}

function save_main() {
    var flag = true;
    var postData = {};
    $("#my_modal input,#my_modal select,#my_modal textarea").each(function () {
        var name = $(this).attr("name");
        if($(this).attr("required") && !$(this).val()){
            flag = false;
            return;
        }
        postData[name] = $(this).val();
    });
    if(!flag) return;
    $.ajax({
        url:"/core/menuUrl/saveMain.do",
        data:postData,
        async:true,
        dataType:"json",
        success:function (es) {
            $.messager.alert("操作提示",es.msg,"info");
            if(es.error == 0){
                $("#my_modal").modal('hide');
                loadTable();
            }
        }
    })
}

function delete_main() {
    if(selectedRow == null){
        $.messager.alert("操作提示","请先选择一行数据","info");
        return;
    }
    $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
        if (data) {
            $.post(
                "/core/menuUrl/deleteMain.do",
                {"mainId":selectedRow.urlId},
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
function loadTable() {
    selectedRow = null;
    myTable.ghTable();
}
function refresh_main() {

}
function search_main() {
    $("#serach_form").modal('show');
}