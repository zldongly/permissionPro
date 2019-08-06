$(function () {

    // 修改密码对话框
    $("#pwd_dialog").dialog({
        title: '请输入新密码',
        width: 300,
        height: 200,
        closed: true,
        buttons: '#pwd_dialog_bt'
    });


   // 更新按钮
   $("#btn_update").click(function () {
       $("#pro_form").form("submit", {
           url: '/updateProfile',
           success: function (data) {
               data = $.parseJSON(data);
               $.messager.alert("提示", data.msg);
           }
       })
   });

   // 修改密码按钮
    $("#btn_update_pwd").click(function () {
        $("#pwd_form").form("clear");
        $("#pwd_dialog").dialog("open");

    });

   // 修改密码确认按钮
    $("#save").click(function () {
        // 判断两次密码是否相同
        var newPwd = $("#pwd_new").val();
        var againPwd = $("#pwd_again").val();
        if (newPwd != againPwd) {
            alert("两次密码不同");
            return;
        }

        $("#pwd_form").form("submit", {
            url: '/updatePwd',
            success: function (data) {
                data = $.parseJSON(data);
                $.messager.alert("提示", data.msg);
                if (data.success) {
                    $("#pwd_dialog").dialog("close");
                }
            }
        })
    })

});