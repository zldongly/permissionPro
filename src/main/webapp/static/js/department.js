$(function () {

    // 数据表格
    $("#dep_dg").datagrid({
        url: "/departmentList",
        columns: [[
            {field: 'name', title: "部门名称", width: 1, align: 'center'}
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#dep_toolbar'
    });

    // 对话框
    $("#dep_dialog").dialog({
        width: 320,
        height: 'auto',
        buttons: [{
            text: '保存', handler: function () {   // 提交表单
                // 判断功能
                var id = $("[name='id']").val();
                var url;
                if (id) {
                    url = "/updateDep"
                } else {
                    url = "/addDep"
                }

                $("#dep_form").form("submit", {
                    url: url,
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("提示", data.msg);
                            $("#dep_dialog").dialog("close");   // 关闭对话框
                            $("#dep_dg").datagrid("reload");    // 重新加载
                        } else {
                            $.messager.alert("提示", data.msg);
                        }
                    }
                });
            }
        }],
        closed: true
    });


    // 添加按钮
    $("#add").click(function () {
        $("#dep_form").form("clear");   // 清空表单
        $("#dep_dialog").dialog("setTitle", "添加部门");
        $("#dep_dialog").dialog("open");        // 显示对话框
    });

    // 编辑按钮
    $("#edit").click(function () {
        // 获取选中行
        var rowData = $("#dep_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "请选择一行数据");
            return;
        }

        $("#dep_form").form("clear");   // 清空表单
        // 弹出对话框
        $("#dep_dialog").dialog("setTitle", "编辑部门");
        $("#dep_dialog").dialog("open");

        $("#dep_form").form("load", rowData);

    });

    // 删除按钮
    $("#delete").click(function () {
        // 获取选中行
        var rowData = $("#dep_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "请选择一行数据");
            return;
        }
        // 确认删除
        $.messager.confirm("确认", "是否做删除部门操作", function (res) {
            if(res) {   // 确认删除
                $.post("/deleteDep", {id: rowData.id}, function (data) {
                    if (typeof(data) == "string") {
                        data = $.parseJSON(data);
                    }
                    if (data.success) {
                        $.messager.alert("提示", data.msg);
                        $("#dep_dg").datagrid("reload");    // 重新加载
                    } else {
                        $.messager.alert("提示", data.msg);
                    }
                });
            }
        })
    });

});