<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 2019/7/15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>权限管理系统</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
</head>
<body class="easyui-layout">

<%--顶部--%>
<div data-options="region:'north'" style="height:100px; background: #ec4e00; padding: 20px 20px; position: relative;">
    <img src="static/images/main_logo.png" alt="">
    <div style="position: absolute; right: 50px; top: 30px;">
<%--        <img src="./static/images/user.png" style="vertical-align: middle; margin-right: 10px;" >--%>
        <%-- 用户名 --%>
        <span style="color: white; font-size: 20px; margin-right: 5px;"><shiro:principal property="username" /> </span>
        <%-- logout --%>
        <a style="font-size: 18px; color: white;text-decoration: none;" href="${pageContext.request.contextPath}/logout">注销</a>
    </div>
</div>

<%--底部--%>
<div data-options="region:'south'" style="height:50px; border-bottom: 3px solid #ec4e00">
    <p align="center" style="font-size: 14px">zldongly@163.com</p>
</div>

<%--左侧菜单--%>
<div data-options="region:'west',split:true" style="width:300px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">
        <div title="菜单" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">
            <!--tree-->
            <ul id="tree"></ul>
        </div>
        <div title="公告" data-options="iconCls:'icon-reload'" style="padding:10px;">
        </div>
    </div>
</div>

<%--主区域--%>
<div data-options="region:'center'" style="background:#eee;">
    <!--标签-->
    <div id="tabs" style="overflow: hidden">
    </div>
</div>

</body>
</html>
