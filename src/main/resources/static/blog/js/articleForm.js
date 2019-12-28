
$(document).ready(function () {
    sourceListener();
    isShowOriginal();
    formSubmit();
    form.render();
});

//对来源的监听
function sourceListener() {
    form.on("radio(source)", function (data) {
        if (data.value == 0) {
            $("#af_original").hide();
        } else if (data.value == 1) {
            $("#af_original").show();
        }
    });
}

//转载时显示原作者和链接
function isShowOriginal() {
    var mode = $("#mode").val();
    var source = $("input[type=radio][name=source]:checked").val();
    if (mode=='edit'&&source==1){
        $("#af_original").show();
    }
}

//表单校验、异步提交
function formSubmit() {
    //表单校验
    $("#articleForm").validate({
        submitHandler: function (form) {
            layer.closeAll();
            $(form).ajaxSubmit({
                success: function (data) {
                    if (data.module.id) {
                        if (mode == 'add') {
                            layer.msg("新增成功 !", {time: 2000, offset: '66px'});
                        } else {
                            layer.msg("修改成功 !", {time: 2000, offset: '66px'});
                        }
                        articleTable.reload('articleListTable');
                    } else {
                        layer.msg("操作失败", {time: 2000, offset: '66px'});
                    }
                },
                error: function () {
                    layer.msg("操作失败", {time: 2000, offset: '66px'});
                }
            })
        }
        ,rules:{}
        ,messages:{}
    });
}