<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Bootstrap 实例 - 模态框（Modal）插件方法</title>



    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <!--js-->
    <script type="text/javascript" src="/public/jquery/jquery.js"></script>
    <script type="text/javascript" src="/public/eiis/eiis.js"></script>
    <script type="text/javascript" src="/public/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/theme/pc/main/css_js/index.js"></script>
    <script type="text/javascript">
        var menuTreeId = "";
        var menuList = '';
    </script>

    <!--设置浏览器窗口图标-->
    <link rel="shortcut icon" type="image/x-icon" href="/theme/pc/main/img/logoIco.ico"media="screen" />

    <!--css-->
    <link rel="stylesheet" href="/public/bootstrap/css/bootstrap.min.css"/>

    <link rel="stylesheet" href="/theme/pc/main/css_js/index.css"/>

    <script type="text/javascript">
        EIIS.Common.loadComponent(EIIS.Common.bootstrap.BootstrapTable);
    </script>

    <link  rel="stylesheet" href="/app/typeInfo/css_js/index.css" />

</head>
<body>

<h2>模态框（Modal）插件方法</h2>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    开始演示模态框
</button>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
                按下 ESC 按钮退出。
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>