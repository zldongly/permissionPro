<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 2019/7/19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/menu.js"></script>
</head>
<body>

<%--数据表格--%>
<table id="menu_datagrid">
    <thead>
    <tr>
        <th>名称</th>
        <th>url</th>
        <th>父菜单</th>
    </tr>
    </thead>
</table>

<%--工具栏--%>
<div id="menu_toolbar">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" id="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="del">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="reload">刷新</a>
    </div>
</div>

<%--对话框--%>
<div id="menu_dialog">
    <form id="menuForm" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">
            <tr>
                <td>名称</td>
                <td><input type="text" name="text"></td>
            </tr>
            <tr>
                <td>请求地址</td>
                <td><input type="text" name="url"></td>
            </tr>
            <tr>
                <td>父菜单</td>
                <td><input type="text" id="parentMenu" name="parent.id" class="easyui-combobox" placeholder="请选择父菜单"/></td>
            </tr>
        </table>
    </form>
</div>

<!-- 对话框保存按钮 -->
<div id="menu_dialog_bt">
    <a class="easyui-linkbutton" id="save" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
</div>

</body>
</html>
