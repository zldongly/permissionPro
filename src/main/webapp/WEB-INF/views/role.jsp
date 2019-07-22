<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 2019/7/19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Role</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/role.js"></script>
    <style>
        #dialog #roleForm .panel-header{
            height: 28px;
        }
        #dialog #roleForm .panel-title{
            color: black;
            margin-top: -5px;
        }
    </style>
</head>
<body>

<%--按钮 工具栏--%>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-add', plain: true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-edit', plain: true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-remove', plain: true" id="delete">删除</a>
</div>

<%--数据展示--%>
<div id="role_dg"></div>

<%--对话框--%>
<div id="dialog">
    <form id="roleForm" method="post">
        <table align="center" style="border-spacing: 20px 30px">
            <input type="hidden" name="rid">
            <tr align="center">
                <td>角色编号: <input type="text" name="rnum" class="easyui-validatebox" ></td>
                <td>角色名称: <input type="text" name="rname" class="easyui-validatebox" ></td>
            </tr>
            <tr>
                <td><div id="role_data1"></div></td>
                <td><div id="role_data2"></div></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
