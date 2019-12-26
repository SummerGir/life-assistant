var myTable = $("#myTableTest");
var selecteRow;
var loading = false;//控制项目列表频繁点击
var option = {
    id:"#myTableTest",//需要绑定id或class
    url:"/app/typeInfo/getMainInfo.do",//表格请求的路径
    isPage:false,
    columns:[
        {name:"typeName",title:"类型名称",align:"lefe",width:"50%"},
        {name:'typeCode',title:'类型编码',align:'left'}
    ]//表格列[{field:'name',title:'名称',align:'left',width:80,template:function(){}},{},{
}

$(window).load(function () {
    myTable.ghTable(option);
    myTable.on("table.create",function () {
        loading = false;
    })
    myTable.on("table.row.selected",function (event,eventData) {
        selecteRow = eventData.row;
        if(selecteRow != null){

        }
    })
})