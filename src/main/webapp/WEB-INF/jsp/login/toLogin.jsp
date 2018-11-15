<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登陆页面</title>
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/css/bootstrap.min.css" />
    <style type="text/css">
        body{
            background: url("${pageContext.request.contextPath}/resources/image/login_1.jpg");
            animation-name:myfirst;
            animation-duration:12s;
            /*变换时间*/
            animation-delay:2s;
            /*动画开始时间*/
            animation-iteration-count:infinite;
            /*下一周期循环播放*/
            animation-play-state:running;
            /*动画开始运行*/
        }
        @keyframes myfirst
          {
            0% {background:url("${pageContext.request.contextPath}/resources/image/login_1.jpg");}
            34% {background:url("${pageContext.request.contextPath}/resources/image/login_2.jpg");}
            67% {background:url("${pageContext.request.contextPath}/resources/image/login_3.jpg");}
            100% {background:url("${pageContext.request.contextPath}/resources/image/login_1.jpg");}
        }
        .form{background: rgba(255,255,255,0.2);width:400px;margin:120px auto;}
        /*阴影*/
        .fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
        input[type="text"],input[type="password"]{padding-left:26px;}
        .checkbox{padding-left:21px;}
    </style>
</head>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">LOGIN</h3>
            <div class="col-xs-9">
                <div class="form-group">
                    <input class="form-control required" type="text" placeholder="请输入员工号" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <input class="form-control required" type="password" placeholder="请输入密码" id="password" name="password" maxlength="8"/>
                </div>
                <%--<div class="form-group list-inline">--%>
                    <%--<input class="form-control required" type="text" placeholder="请输入验证码" id="verifCode" name="verifCode" maxlength="8"/>--%>
                <%--</div>--%>
                <div class="form-group list-inline">
                    <div>
                    <input class="form-control required" type="text" placeholder="请输入验证码" id="verifCode" name="verifCode" maxlength="8"/>
                     <img src="${pageContext.request.contextPath}/login/getkaptchaCode.do" id="kaptchaImage"style="cursor:pointer">
                    </div>
                </div>
                <div class="form-group">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1"/>记住我
                    </label>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success pull-left  btn-lg" name="submit">登录</button>
                    <button type="submit" class="btn btn-default  pull-right  btn-lg  active" name="submit">
                        <div class="text-success">注册</div>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
