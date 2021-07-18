//upload
$(function () {

    //upload 上传
    $("#attachmentUploadBtn").click(function () {
        //1、通过HTML表单创建FormData对象 自动注入
        // var formData = new FormData($("#attachments")[0]);

        //2、从零开始创建FormData对象 手动注入
        var formData = new FormData();
        //注入 name=file
        var files = $("#attachmentInputs input[type='file']");
        for (var i = 0; i < files.length; i++) {
            //注意：这里append进去的是File对象，而不是FileList对象
            formData.append("uploadfile", files[i].files[0]);
        }
        //注入name=text
        var flagNum = $("#num").val();
        formData.append("flagNum", flagNum);

        console.log(formData.getAll("uploadfile"));

        //执行上传
        $.ajax({
            url: "upload",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                var message = data.message;
                if (message.success) {
                    alert(message.msg);
                } else {
                    alert(message.msg);
                }
            },
            error: function (e) {
                alert(e);
            }
        });
    });

    //calculation 计算
    $("#buttons-btn").click(function () {
        var flagNum = $("#flagNum-id").val();
        if (flagNum == "" || flagNum == null){
            alert("标识符不能为空");
            return;
        }
        $.ajax({
           url: "calculation",
           type: "post",
           data: {"flagNum":flagNum},
           success:function (data) {
               var message = data.message;
               if (message.success) {
                   alert(data.data.result);
               } else {
                   alert(data.data.result);
               }
           },
           error: function (e) {
               alert(e);
           }
        });
    });

    //buttons-down 下载
    $("#buttons-down").click(function () {
        var flagNum = $("#flagNum-id").val();
        $.ajax({
            url: "export",
            type: "post",
            // async: false,
            data: {"flagNum":flagNum},
            success: function (data) {
                var message = data.message;
                if (message.success){
                    if (message.msg != "文件创建成功"){
                        alert(message.msg);
                    }
                    var filePath = data.data.FILE_PATH;
                    download("download","filePath",filePath,"post");
                }else {
                    alert(message.msg);
                }
            }
        });
    });

    $("#button-model").click(function(){
        var id = $("#fileModel").val();
        $.ajax({
            url: "exportFileModel",
            type: "post",
            data: {"id": id},
            success: function(data){
                alert(data);
                download("download","filePath",data,"post")
            },
            error: function (e) {
                alert("接口调用异常");
            }
        });
    });
});

/**
 * 正常情况下ajax请求的是字符型的数据，但文件返回的是字节型的数据  故正常情况ajax是下载不了文件的
 *
 * 如下是使用ajax请求下载文件的 解决方案：
 * 按钮id绑定一个ajax请求 查询出的数据 整理成一个文件上传至服务器  返回一个服务器中存储文件的地址
 *          或者  直接查询服务器中的文件地址 返回
 * 拼成一个form表单请求 传参文件地址 即可下载文件
 *
 * @param url 请求的地址
 * @param name input表单中的name值
 * @param value input表单中的value值
 * @param method 请求的类型 post / get
 */
function download(url,name,value,method) {
    $('<form action="'+url+'" method="'+method+'"><input type="text" name="'+name+'" value="'+value+'"/></form>')
        .appendTo("body").submit().remove();
}

