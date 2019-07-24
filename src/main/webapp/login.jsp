<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 2019/7/23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html lang="zh">
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/static/css/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/plugins/easyui/jquery.min.js"></script>
    <script>
        $(function () {
            $("#loginBtn").click(function () {

                $.post("/login", $("form").serialize(), function (data) {
                    data = $.parseJSON(data);   // string 转 json
                    if (data.success) {
                        window.location.href = "/index.jsp"
                    } else {
                        //console.log(data.msg);
                        alert(data.msg);
                    }
                });
            });
        });
    </script>
</head>

<body class="white">
<div class="login-hd">
    <div class="left-bg"></div>
    <div class="right-bg"></div>
    <div class="hd-inner">
        <span class="logo"></span>
        <span class="split"></span>
        <span class="sys-name">用户权限管理系统</span>
    </div>
</div>
<div class="login-bd">
    <div class="bd-inner">
        <div class="inner-wrap">
            <div class="lg-zone">
                <div class="lg-box">
                    <div class="lg-label"><h4>用户登录</h4></div>

                    <form>
                        <div class="lg-username input-item clearfix">
                            <i class="iconfont"></i>
                            <input type="text" value="dong" name="username" placeholder="请用户名">
                        </div>
                        <div class="lg-password input-item clearfix">
                            <i class="iconfont"></i>
                            <input type="password" value="123" name="password" placeholder="请输入密码">
                        </div>

                        <div class="enter">
                            <a href="javascript:;" class="purchaser" id="loginBtn">点击登录</a>
                        </div>

                    </form>
                    <div class="line line-y"></div>
                    <div class="line line-g"></div>
                </div>
            </div>
            <div class="lg-poster"></div>
        </div>
    </div>
</div>
<div class="login-ft">
    <div class="ft-inner">
        <div class="other-info">E-mail：zldongly@163.com</div>
    </div>
</div>


<script type="text/javascript">

</script>
</body>
</html>
