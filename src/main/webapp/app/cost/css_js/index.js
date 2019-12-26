var myTable = $("#myTableTest");
var selecteRow;
var loading = false;//控制项目列表频繁点击
var option = {
    id:"#myTableTest",//需要绑定的Id或class
    url:"/app/cost/getMainInfo.do",//表格请求的路径
    data:{},
    toolbar:"#main_table_customRibbon",//表格上面的工具栏用哪个容器
    columns:[
        {name:'typeDetailName',title:"消费类型",align:'left',width:'20%'},
        {name:'payMoney',title:"消费金额",align:'right',width:'15%'},
        {name:'costNum',title:"消费数量",align:'right',width:'15%'},
        {name:'costPrice',title:"消费价格",align:'right',width:'15%'},
        {name:'costTime',title:"消费日期",align:'center',width:'15%'},
        {name:'title',title:'备注说明',align:'left'}
    ]//表格列[{field:'name',title:'名称',align:'left',width:80,template:function(){}},{},{}]
};
$(window).load(function () {
    clone_my_nav("need-nav");
    myTable.ghTable(option);

    myTable.on("table.created",function(){
        loading = false;
    })
    //行选中
    myTable.on("table.row.selected",function (event,eventData) {
        selecteRow = eventData.row;
    })
});


function loadTable() {
    selecteRow = null;//刷新列表前，把选中行设置为空
    myTable.ghTable();//刷新列表，可以不传参
}