var myDetailTable = $("#myDetailTable");
var selectedDetailRow;
var option_detail = {
    id:"#myDetailTable",
    url:"/app/typeInfo/getDetailInfo.do",
    rows:10,
    columns:[
        {name:'detailName',title:"标题",align:'left',width:'15%'},
        {name:'detailCode',title:'编码',align:'left',width:'15%'},
        {name:'detailValue',title:'值',align:'center',width:'18%'},
        {name:'detailLevel',title:"顺序",align:'center',width:'18%'},
        {name:'isValid',title:"是否有效",align:'center',width:'10%'},
        {name:'comment',title:"备注",align:'center'}
    ]
};
$(window).load(function () {
    myDetailTable.ghTable(option_detail);
    myDetailTable.on("table.row.selected",function (event,eventData) {
        selectedDetailRow = eventData.row;
    });
    myDetailTable.on("table.column.isValid.foramt", function (a, eventData) {
        var row = eventData.row;
        if(row.isValid == "true"){
            eventData.ed.html("<span style='color:#5cb85c'>有效</span>");
        }else{
            eventData.ed.html("<span style='color:#d9534f'>无效</span>");
        }

    });
});
function add_detial() {
    if(selecteRow == null){
        alert("请在左侧选中一项数据类型");
        return;
    }
    $("#my_detial_modal input,#my_detial_modal select,#my_detial_modal textarea").each(function () {
        $(this).val("");
    })
    $("#my_detial_modal *[name='typeId']").val(selecteRow.typeId);
    $("#my_det_modal *[name='isValid']").val("true");
   $("#my_detial_modal").modal('show');
}
function edit_detial() {
    if(selectedDetailRow == null){
        alert("请先选中一项数据类型");
        return;
    }
    $("#my_detial_modal input,#my_detial_modal select,#my_detial_modal textarea").each(function () {
       var name = $(this).attr("name");
       $(this).val(selectedDetailRow[name]);
    })
    $("#my_detial_modal").modal('show');
}
function delete_detial() {
    if(selectedDetailRow == null){
        alert("请先选中一项数据类型");
        return;
    }
    $.post(
        '/app/typeInfo/deleteDetial.do',
        {typeDetailId : selectedDetailRow.typeDetailId},
        function (rs) {
            console.log(rs.msg);
            if(rs.error == 0){
                loadDetialTable();
            }
        },
        'json'
    );
}
function save_detial() {
    var postData = get_detial_data();
    console.log(postData);
    if(postData){
        $.ajax({
            url:'/app/typeInfo/saveDetial.do',
            data:postData,
            type:'post',
            async:true,
            dataType:'json',
            success:function (rs) {
                console.log(rs.msg);
                if(rs.error == 0){
                    loadDetialTable();
                    $("#my_detial_modal").modal('hide');
                }
            }
        });
    }
}
function get_detial_data() {
    var flag = true;
    var postData ={};
    $("#my_detial_modal input,#my_detial_modal select,#my_detial_modal textarea").each(function () {
        $(this).css("border","1px solid #ccc");
        var name = $(this).attr("name");
        if($(this).attr("required") && !$(this).val()){
            $(this).css("border","1px solid red");
            var text = "请输入"+$(this).prev().text;
            text = text.replace(":","");
            alert(text);
            flag = false;
            return false;
        }else {
            postData[name] = $(this).val();
        }

    });
    if(!flag) return;
    console.log(postData);
    return postData;
}
function loadDetialTable() {
    selectedDetailRow = null;
    myDetailTable.ghTable();
}
























