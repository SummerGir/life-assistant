<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/16
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="/public/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/theme/pc/main/css_js/index.css" >
    <script type="text/javascript" src="/public/jquery/jquery.js"></script>
    <script type="text/javascript" src="/theme/pc/main/css_js/index.js"></script>
</head>
<body>
<div class="container">
    <div class="form row">
        <form class="form-horizontal col-sm-offset-4 col-md-offset-4" id="login_form">
            <h3 class="form-title">Login to yout accout</h3>
            <div class="col-md-6 col-sm-6">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="username" name="username" autofocus="autofocus" maxlength="20">
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="password" name="password" maxlength="8"/>
                </div>
                <div class="form-group">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1"/>remember me
                    </label>
                    <hr/>
                    <a href="javascript:;" id="register_btn" class="">Create an account</a>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success pull-right" value="Login "/>
                </div>
            </div>

        </form>

    </div>



    <div class="form row">
        <form class="form-horizontal col-sm-offset-4 col-md-offset-4" id="register_form">
            <h3 class="form-title">Login to your account</h3>
            <div class="col-sm-6 col-md-6">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="Username" name="username" autofocus="autofocus"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="Password" id="register_password" name="password"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-check fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="Re-type Your Password" name="rpassword"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-envelope fa-lg"></i>
                    <input class="form-control eamil" type="text" placeholder="Email" name="email"/>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success pull-right" value="Sign Up "/>
                    <input type="submit" class="btn btn-info pull-left" id="back_btn" value="Back"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
