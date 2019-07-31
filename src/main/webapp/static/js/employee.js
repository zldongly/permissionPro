$(function () {

    // 信息显示
    $("#dg").datagrid({
        url: "/employeeList",
        columns: [[
            {field: 'username', title: '姓名', width: 100, align: 'center'},
            {field: 'inputTime', title: '入职时间', width: 100, align: 'center'},
            {field: 'tel', title: '电话', width: 100, align: 'center'},
            {field: 'email', title: '邮箱', width: 100, align: 'center'},
            {
                field: 'department', title: '部门', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return value.name;
                    }
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (row.state) {
                        return "在职";
                    } else {
                        return "<span style='color: red; '>离职</span>";
                    }
                }
            },
            {
                field: 'admin', title: '管理员', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.admin) {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            }
        ]],
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#tb",
        onClickRow: function (rowIndex, rowData) {
            if (rowData.state) {
                $("#remove").linkbutton("enable");
            } else {
                $("#remove").linkbutton("disable");
            }
        }
    });

    // 对话框
    $("#dialog").dialog({
        width: 350,
        height: 'auto',
        buttons: [{
            text: '保存', handler: function () {   // 提交表单

                // 判断功能
                var id = $("[name='id']").val();
                var url;
                if (id) {
                    url = "/updateEmployee"
                } else {
                    url = "/addEmployee"
                }

                $("#employeeForm").form("submit", {
                    url: url,
                    onSubmit: function (param) {
                        // 获取选中的角色
                        var values = $("#role").combobox("getValues");
                        for (var i = 0; i < values.length; i++) {
                            var rid = values[i];
                            param["roles[" + i + "].rid"] = rid;
                        }
                    },
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("提示", data.msg);
                            $("#dialog").dialog("close");   // 关闭对话框
                            $("#dg").datagrid("reload");    // 重新加载
                        } else {
                            $.messager.alert("提示", data.msg);
                        }
                    }
                });
            }
        }],
        closed: true
    });

    // 部门选择 下拉列表
    $("#department").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: '/departList',
        textField: 'name',
        valueField: 'id',
        onLoadSuccess: function () {
            // 显示提示
            $("#department").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            })
        }
    });

    // 管理员选择
    $("#state").combobox({
        width: 150,
        panelHeight: 'auto',
        valueField: 'value',
        textField: 'label',
        editable: false,
        data: [
            {label: '是', value: 'true'},
            {label: '否', value: 'false'}
        ],
        onLoadSuccess: function () {
            // 显示提示
            $("#state").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            })
        }
    });

    // 角色下拉列表
    $("#role").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: '/roleList',
        textField: 'rname',
        valueField: 'rid',
        multiple: true,
        onLoadSuccess: function () {
            // 显示提示
            $("#role").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            })
        }
    });

    // 上传对话框
    $("#excelUpload").dialog({
        width:260,
        height:180,
        title:"导入Excel",
        buttons:[{
            text:'保存',
            handler:function(){
                $("#uploadForm").form("submit", {
                    url: '/uploadExcel',
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("提示", data.msg);
                            $("#excelUpload").dialog("close");   // 关闭对话框
                            //$("#dg").datagrid("reload");    // 重新加载
                        } else {
                            $.messager.alert("提示", data.msg);
                        }
                    }
                })
            }
        }],
        closed:true
    });


    // 添加按钮
    $("#add").click(function () {
        $("#employeeForm").form("clear");   // 清空表单
        $("[name='password']").validatebox({required: true});
        $("#password").show();                  // 显示密码栏
        $("#dialog").dialog("setTitle", "添加员工");
        $("#dialog").dialog("open");        // 显示对话框
    });

    // 编辑按钮
    $("#edit").click(function () {
        // 获取选中行
        var rowData = $("#dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "请选择一行数据");
            return;
        }

        $("#employeeForm").form("clear");   // 清空表单
        // 回显
        if (rowData['department']) {     // 显示部门
            rowData['department.id'] = rowData['department'].id;
        }
        if (rowData['admin'] != null) {             // 转成字符串 显示中文
            rowData['admin'] = rowData['admin'] + "";
        }
        $.get("/getRoleByEid?eid=" + rowData.id, function (data) {  // 显示角色
            $("#role").combobox("setValues", data)
        });

        $("[name='password']").validatebox({required: false});
        $("#password").hide();      // 隐藏密码栏
        // 弹出对话框
        $("#dialog").dialog("setTitle", "编辑员工");
        $("#dialog").dialog("open");

        $("#employeeForm").form("load", rowData);

    });

    // 离职按钮
    $("#remove").click(function () {
        // 获取选中行
        var rowData = $("#dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "请选择一行数据");
            return;
        }
        // 确认离职
        $.messager.confirm("确认", "是否做离职操作", function (res) {
            if(res) {   // 确认离职
                $.get("/updateState?id=" + rowData.id, function (data) {
                    //data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("提示", data.msg);
                        $("#dg").datagrid("reload");    // 重新加载
                    } else {
                        $.messager.alert("提示", data.msg);
                    }
                })
            }
        })
    });

    // 刷新按钮
    $("#reload").click(function () {
        var keyword = $("[name='keyword']").val('');    // 清空搜索
        $("#dg").datagrid("load", {});
    });

    // 搜索按钮
    $("#search").click(function () {
        var keyword = $("[name='keyword']").val();
        $("#dg").datagrid("load", {keyword: keyword});
    });

    // 导出按钮
    $("#excelOut").click(function () {
        window.open('/downloadExcel');
    });

    // 导入按钮
    $("#excelImport").click(function () {
        $("#excelUpload").dialog("open");
    });


    // 下载Excel模板
    $("#downloadTml").click(function () {
        window.open("/downloadExcelTpl");
    });

});