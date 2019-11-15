
$(function () {
    initLayui();
    initGirl();
});

//初始化layui
function initLayui(){
    ;!function(){
        //无需再执行layui.use()方法加载模块，直接使用即可
        var form = layui.form
            ,layer = layui.layer;
    }();
}

function initGirl() {
    L2Dwidget.init({ "model": { jsonPath:
                "https://unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json",
            "scale": 1 }, "display": { "position": "left", "width": 150, "height": 300,
            "hOffset": 0, "vOffset": -20 }, "mobile": { "show": true, "scale": 0.5 },
        "react": { "opacityDefault": 0.7, "opacityOnHover": 0.2 }
    });
}