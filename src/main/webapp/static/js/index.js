$(function () {
    $("#tabs").tabs({
        fit: true
    });

    $('#tree').tree({
        url: "/getTreeData",
        lines: true,
        onSelect: function (node) {
            /*在添加之前, 做判断  判断这个标签是否存在 */
            var exists = $("#tabs").tabs("exists", node.text);
            if (exists) {
                /*存在,就让它选中*/
                $("#tabs").tabs("select", node.text);
            } else {
                if (node.url != '' && node.url != null) {
                    /*如果不存在 ,添加新标签*/
                    $("#tabs").tabs("add", {
                        title: node.text,
                        /*href:node.attributes.url,*/  /*href  引入的是body当中*/
                        content: "<iframe src=" + node.url + " frameborder='0' width='100%' height='100%'></iframe>",
                        closable: true
                    })
                }
            }
        },
        onLoadSuccess: function (node, data) {
            // 默认打开个人信息界面
            var u;
            for (var i = 0; i < data.length; i++) {
                if (data[i].url == '/profile') {
                    u = i;
                    break;
                }
            }
            var n = $('#tree').tree('find', data[u].id);
            //调用选中事件
            $('#tree').tree('select', n.target);
        }
    });
});
