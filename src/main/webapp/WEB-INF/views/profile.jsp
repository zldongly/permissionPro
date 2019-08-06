<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 2019/8/6
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="/static/common/common.jsp" %>
    <link href="${pageContext.request.contextPath}/static/css/profile.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/profile.js"></script>
</head>
<body>

<div class="pro_box">
    <form id="pro_form" method="post">
        <div class="pro_un">
            <span class="pro_ti">用户名</span> <br>
            <input type="text" class="pro_in" value="${employee.username}" disabled>
        </div>

        <div class="pro_un">
            <span class="pro_ti">入职时间</span> <br>
            <input type="text" class="pro_in" value="${inputDate}" disabled>
        </div>

        <div class="pro_un">
            <span class="pro_ti">电话</span> <br>
            <input type="text" class="pro_in" value="${employee.tel}" name="tel">
        </div>

        <div class="pro_un">
            <span class="pro_ti">邮箱</span> <br>
            <input type="text" class="pro_in" value="${employee.email}" name="email">
        </div>

        <div class="pro_un">
            <span class="pro_ti">部门</span><br>
            <input type="text" class="pro_in" value="${employee.department.name}" disabled>
        </div>
    </form>
    <a href="#" id="btn_update" class="easyui-linkbutton" style="margin-top: 10px; margin-left: 30px">更新</a>
    <a href="#" id="btn_update_pwd" class="easyui-linkbutton" style="margin-top: 10px; margin-left: 60px">修改密码</a>
</div>

<%--修改密码 对话框--%>
<div id="pwd_dialog">
<form id="pwd_form" method="post">
    <table align="center" style="margin-top: 15px;">
        <tr>
            <td>新密码</td>
            <td><input type="password" name="newPassword" id="pwd_new" class="easyui-validatebox" data-options="required:true"></td>
        </tr>
        <tr>
            <td>重复新密码</td>
            <td><input type="password" id="pwd_again" class="easyui-validatebox" data-options="required:true"></td>
        </tr>
    </table>
</form>
</div>
<%--对话框保存按钮--%>
<div id="pwd_dialog_bt">
    <a class="easyui-linkbutton" id="save" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
</div>

</body>
</html>
