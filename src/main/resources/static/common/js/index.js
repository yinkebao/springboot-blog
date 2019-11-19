
$(function () {
    initLayui();
    init2DImg();
    setSwiperImgs();
    initSwiper();
});
//初始化swiper
function initSwiper() {
    var mySwiper = new Swiper ('.swiper-container', {
        autoplay: true,             //自动滑动
        direction: 'horizontal',    // 垂直切换选项 horizontal（横向） vertical（垂直）
        loop: true,                 // 循环模式选项
        grabCursor: true,           //鼠标放入变成手型
        effect: "coverflow",             //切换效果 'slide'（普通切换、默认）,"fade"（淡入）"cube"（方块）"coverflow"（3d流）"flip"（3d翻转）
        mousewheelControl : true,   //鼠标滑动控制
        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination'
        }
        // // 如果需要前进后退按钮
        // navigation: {
        //     nextEl: '.swiper-button-next',
        //     prevEl: '.swiper-button-prev'
        // }
        // 如果需要滚动条
        // scrollbar: {
        //     el: '.swiper-scrollbar',
        // },
    });
    mySwiper.enableMousewheelControl();
}

//设置轮播图背景图片
function setSwiperImgs() {
    var swipers =[
        {
            id:1,
            img:"/common/img/swiper1.png",
            textName:"第一个标题"
        },
        {
            id:2,
            img:"/common/img/swiper2.png",
            textName:"第二个标题"
        },
        {
            id:3,
            img:"/common/img/swiper3.png",
            textName:"第三个标题"
        }
    ];
    swipers.forEach(function (value) {
        $("#container").append('<div class="swiper-slide" style="background: url('+value.img+') no-repeat;background-size: 100% 100%;background-position:center;">' +
            '<span>'+value.textName+'</span>' +
            '</div>');
    });
}

//初始化layui
function initLayui(){
    ;!function(){
        //无需再执行layui.use()方法加载模块，直接使用即可
        var form = layui.form
            ,layer = layui.layer;
    }();
}

//初始化2d动画人
function init2DImg() {
    L2Dwidget.init({
        "model": {
            jsonPath:
                "https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json",
            "scale": 1
        }, "display": {
            "position": "left", "width": 85, "height": 220,
            "hOffset": 0, "vOffset": -20
        }, "mobile": {"show": true, "scale": 0.5},
        "react": {"opacityDefault": 0.7, "opacityOnHover": 0.2}
    });
}