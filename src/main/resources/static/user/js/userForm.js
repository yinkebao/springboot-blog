var mode = $("#mode").val();

//监控开关-是否管理员
form.on('switch(uAdmin)', function(data){
    $("#uAdmin").val(data.elem.checked);
    // console.log(data.elem); //得到checkbox原始DOM对象
    // console.log(data.elem.checked); //开关是否开启，true或者false
    // console.log(data.value); //开关value值，也可以通过data.elem.value得到
    // console.log(data.othis); //得到美化后的DOM对象
});

$(document).ready(function () {
    form.render();
    //表单校验
    $("#userForm").validate({
        submitHandler: function (form) {
            layer.closeAll();
            $(form).ajaxSubmit({
                success: function (data) {
                    if (data.module.id){
                        if (mode == 'add'){
                            layer.msg("新增成功 !",{time: 2000,offset:'66px'});
                        }else {
                            layer.msg("修改成功 !",{time: 2000,offset:'66px'});
                        }
                        userTable.reload('userListTable');
                    }else {
                        layer.msg("操作失败",{time: 2000,offset:'66px'});
                    }
                },
                error: function () {
                    layer.msg("操作失败",{time: 2000,offset:'66px'});
                }
            })
        }
        ,rules: {
            "userName":{
                required:true
            }
            ,"password":{
                required:true,
                maxlength:16,
                minlength:6,
                check_pw:true
            }
            ,"rePassword":{
                required:true,
                equalTo:"#password"
            }
            ,"phone":{
                required:true,
                minlength : 11,
                isMobile: true
            }
            ,"email":{
                email: true
            }
        }
        ,messages: {
            "userName":{
                required:"请输入用户名"
            }
            ,"password":{
                required:"请输入密码",
                maxlength:"密码最大长度16",
                minlength:"密码最小长度6",
                check_pw:"请填写正确格式的密码"
            }
            ,"rePassword":{
                required:"请确认密码",
                equalTo:"输入的密码不一致"
            }
            ,"phone":{
                required:"请输入手机号",
                minlength : "不能小于11个字符",
                isMobile: "请填写正确的手机号码"
            }
            ,"email":{
                email: "请填写正确的邮箱"
            }
        }
    });
    //密码验证
    jQuery.validator.addMethod("check_pw", function(value, element) {
        var length = value.length;
        var pw = /^\w{3,20}$/;
        return this.optional(element) || (length > 6 && length<16 && pw.test(value));
    }, "请填写正确格式的密码");
    //手机号码验证（不为空时才触发）
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写手机号码");
});