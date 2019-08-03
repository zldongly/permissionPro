<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 2019/8/3
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/department.js"></script>
</head>
<body>

<%--按钮 工具栏--%>
<div id="dep_toolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-add', plain: true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-edit', plain: true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-remove', plain: true" id="delete">删除</a>
</div>

<%--数据展示--%>
<div id="dep_dg"></div>

<%--对话框--%>
<div id="dep_dialog">
    <form id="dep_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="border-spacing: 0px 10px">
            <tr>
                <td>部门名称:</td>
                <td><input type="text" name="name" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
