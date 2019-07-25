$(function () {

    // 数据表格
    $("#menu_datagrid").datagrid({
        url: "/menuList",
        columns: [[
            {field: 'text', title: "菜单", width: 1, align: 'center'},
            {field: 'url', title: "请求地址", width: 1, align: 'center'},
            {
                field: 'parent', title: "父菜单", width: 1, align: 'center', formatter: function (value, row, index) {
                    return value ? value.text : '';
                }
            }
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#menu_toolbar'
    });

    // 初始化对话框
    $("#menu_dialog").dialog({
        width: 300,
        height: 'auto',
        closed: true,
        buttons: '#menu_dialog_bt'
    });

    $("#parentMenu").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: '/parentMenuList',
        textField: 'text',
        valueField: 'id',
        onLoadSuccess: function () {
            // 显示提示
            $("#parentMenu").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            })
        }
    });


    // 对话框 保存 按钮
    $("#save").click(function () {

        var id = $("[name='id']").val();
        var url;
        if (id) {
            var parentId = $("[name='parent.id']").val();
            if (id == parentId) {
                //alert("不能设置自己为父菜单");
                $.messager.alert("警告", "不能设置自己为父菜单");
                return;
            }
            url = "/editMenu"
        } else {
            url = "/addMenu"
        }

        $("#menuForm").form("submit", {
            url: url,
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("提示", data.msg);
                    $("#menu_dialog").dialog("close");          // 关闭对话框
                    $("#parentMenu").combobox("reload");
                    $("#menu_datagrid").datagrid("reload");     // 重新加载
                } else {
                    $.messager.alert("提示", data.msg);
                }
            }
        });
    });


    // 添加按钮
    $("#add").click(function () {
        $("#menuForm").form("clear");
        $("#menu_dialog").dialog("setTitle", "添加菜单");
        $("#menu_dialog").dialog("open");
    });

    // 编辑按钮
    $("#edit").click(function () {
        $("#menuForm").form("clear");
        var rowData = $("#menu_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "请选择一行数据");
            return;
        }
        // 回显 父菜单
        if (rowData.parent) {
            rowData["parent.id"] = rowData.parent.id;
        } else {
            $("#parentMenu").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
        $("#menu_dialog").dialog("setTitle", "编辑菜单");
        $("#menu_dialog").dialog("open");
        // 选中数据回显
        $("#menuForm").form("load", rowData);

    });

    // 删除按钮
    $("#del").click(function () {
        // 获取选中行
        var rowData = $("#menu_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "请选择一行数据");
            return;
        }
        // 确认删除
        $.messager.confirm("确认", "是否做删除菜单操作", function (res) {
            if(res) {   // 确认删除
                $.post("/deleteMenu", {id: rowData.id}, function (data) {
                    //data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("提示", data.msg);
                        $("#menu_datagrid").datagrid("reload");    // 重新加载
                        $("#parentMenu").combobox("reload");
                    } else {
                        $.messager.alert("提示", data.msg);
                    }
                })
            }
        })
    });

});