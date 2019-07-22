$(function () {

    // 角色信息显示
    $("#role_dg").datagrid({
        url: "/getRoles",
        columns: [[
            {field: 'rnum', title: '编号', width: 100, align: 'center'},
            {field: 'rname', title: '名称', width: 100, align: 'center'}
        ]],
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#toolbar"
    });

    // 对话框
    $("#dialog").dialog({
        width: 600,
        height: 500,
        buttons: [{
            text: '保存', handler: function () {   // 提交表单
                // 判断 添加 or 编辑
                var rid = $("[name='rid']").val();
                var url;
                if (rid) {
                    url = "/updateRole";
                } else {
                    url = "/addRole";
                }

                $("#roleForm").form("submit", {
                    url: url,
                    onSubmit: function (param) {        // 在form中添加选中的权限
                        var rows = $("#role_data2").datagrid("getRows");
                        for (var i = 0; i < rows.length; i++) {
                            var row = rows[i];
                            param["permissions[" + i + "].pid"] = row.pid;
                        }
                    },
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("提示", data.msg);
                            $("#dialog").dialog("close");   // 关闭对话框
                            $("#role_dg").datagrid("reload");    // 重新加载
                        } else {
                            $.messager.alert("提示", data.msg);
                        }
                    }
                })
            }
        }],
        closed: true
    });

    // 权限列表
    $("#role_data1").datagrid({
        title: "所有权限",
        width: 250,
        height: 280,
        fitColumns: true,
        singleSelect: true,
        url: '/getPermission',
        columns: [[
            {field: 'pname', title: '权限名称', width: 100, align: 'center'}
        ]],
        onClickRow: function (rowIndex, rowData) {  // 点击权限
            // 判断权限是否已选中
            var rows = $("#role_data2").datagrid("getRows");
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (row.pid == rowData.pid) {
                    var index = $("#role_data2").datagrid("getRowIndex", row);
                    $("#role_data2").datagrid("selectRow", index);
                    return;
                }
            }

            $("#role_data2").datagrid("appendRow", rowData);
        }
    });

    // 选中权限列表
    $("#role_data2").datagrid({
        title: "已选权限",
        width: 250,
        height: 280,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {field: 'pname', title: '权限名称', width: 100, align: 'center'}
        ]],
        onClickRow: function (rowIndex, rowData) {  // 点击已选权限
            $("#role_data2").datagrid("deleteRow", rowIndex);
        }
    });

    // 添加按钮
    $("#add").click(function () {
        $("#roleForm").form("clear");
        $("#role_data2").datagrid("loadData", {rows: []});

        $("#dialog").dialog("setTitle", "添加角色");
        $("#dialog").dialog("open");
    });

    // 编辑按钮
    $("#edit").click(function () {
        // 获取选中行
        var rowData = $("#role_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "请选择一行数据");
            return;
        }

        // 加载选中行的权限
        var options = $("#role_data2").datagrid("options");
        options.url = '/getPermissionByRid?rid=' + rowData.rid;
        $("#role_data2").datagrid("load");

        // 弹出对话框
        $("#dialog").dialog("setTitle", "编辑角色");
        $("#dialog").dialog("open");

        $("#roleForm").form("load", rowData);
    });

    // 删除按钮
    $("#delete").click(function () {
        // 获取选中行
        var rowData = $("#role_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "请选择一行数据");
            return;
        }

        // 确认离职
        $.messager.confirm("确认", "是否做离职操作", function (res) {
            if (res) {   // 确认离职
                $.get("/deleteRole?rid=" + rowData.rid, function (data) {
                    if (data.success) {
                        $.messager.alert("提示", data.msg);
                        $("#dialog").dialog("close");       // 关闭对话框
                        $("#role_dg").datagrid("reload");   // 重新加载
                    } else {
                        $.messager.alert("提示", data.msg);
                    }
                })
            }
        });

    });

});