
$(function () {
    initLayui();
    // init2DImg();
    setSwiperImgs();
    initSwiper();
  // listArticles();
});

//所有文章列表
function listArticles() {
    $.ajax({
      url:"/system/article/listArticles"
      ,success:function (data) {
          data.module.forEach(function (article) {
            var str = '<div class="card_main">';
            str += '<div class="card_bg">';
            str += '<p>';
            str += '<span class="glyphicon glyphicon-tag" style="color: rgb(174, 188, 135); font-size: 14px;">Python</span> '+ article.title;
            str += '</p>';
            str += '<img src="/common/img/article/timthumb.png">';
            str += '<div class="card-content">';
            str +=  article.content;
            str += '</div><div class="card-bottom">';
            str += '<span class="glyphicon glyphicon-user" style="color: rgb(162, 179, 191); font-size: 14px;"></span>';
            str += article.originalAuthor == null? article.author.nickName : article.originalAuthor;
            str += '<span class="glyphicon glyphicon-calendar" style="color: rgb(162, 179, 191); font-size: 14px;"></span>'+article.viewTimes;
            str += '<span class="glyphicon glyphicon-eye-open" style="color: rgb(162, 179, 191); font-size: 14px;"></span>'+article.collectTimes;
            str += '</div></div></div>';
            $("#allArticle").append(str);
          });
      }
    })
}


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
    // mySwiper.enableMousewheelControl();
}

//设置轮播图背景图片
function setSwiperImgs() {
    var swipers =[
        {
            id:1,
            img:"/common/img/swiper1.png",
            textName:""
        },
        {
            id:2,
            img:"/common/img/swiper2.png",
            textName:""
        },
        {
            id:3,
            img:"/common/img/swiper3.png",
            textName:""
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
  layui.use('flow', function() {
    var flow = layui.flow;
    flow.load({
      elem: '#allArticle' //流加载容器
      ,done: function(page, next){ //执行下一页的回调
        var lis = [];
        var pageSize = 0;
        $.ajax({
          url: "/system/article/listArticles",
          data:{
            "page":page,
            "limit":5
          }
          , success: function (data) {
            pageSize = data.module.size;
            data.module.list.forEach(function (article) {
              var str = '<div class="card_main">';
              str += '<div class="card_bg">';
              str += '<p>';
              str += '<span class="glyphicon glyphicon-tag" style="color: rgb(174, 188, 135); font-size: 14px;">Python</span> '+ article.title;
              str += '</p>';
              str += '<a href="/system/article/viewArticle?id='+article.id+'"  target="_blank"><img src="/common/img/article/timthumb.png"></a>';
              str += '<div class="card-content">';
              str +=  article.content;
              str += '</div><div class="card-bottom">';
              str += '<span class="glyphicon glyphicon-user" style="color: rgb(162, 179, 191); font-size: 14px;"></span>';
              str += article.originalAuthor == null? article.author.nickName : article.originalAuthor;
              str += '<span class="glyphicon glyphicon-calendar" style="color: rgb(162, 179, 191); font-size: 14px;"></span>'+new Date(article.publishDate).getFullYear()+'-'+new Date(article.publishDate).getUTCMonth()+'-'+new Date(article.publishDate).getDay();
              str += '<span class="glyphicon glyphicon-eye-open" style="color: rgb(162, 179, 191); font-size: 14px;"></span>'+article.collectTimes;
              str += '</div></div></div>';
              lis.push(str);
            });
          }
        });
        //模拟数据插入
        setTimeout(function(){
          //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
          //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
          next(lis.join(''), page < pageSize); //假设总页数为 10
        }, 500);
      }
    });

  });
}

//查看文章
function viewArticle(id) {
  window.open("/system/article/viewArticle?id="+id);
}

//初始化2d动画人
function init2DImg() {
    L2Dwidget.init({
        "model": {
            jsonPath:
                "https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json",
            "scale": 1
        }, "display": {
            "position": "left", "width": 60, "height": 180,
            "hOffset": '1', "vOffset": -80
        }, "mobile": {"show": true, "scale": 0.5},
        "react": {"opacityDefault": 0.7, "opacityOnHover": 0.2}
    });
}


//蛛丝背景
function async_load() {

    // i.scrolling = "no";
    // i.frameborder = "0";
    // i.border = "0";
    // i.setAttribute("frameborder", "0", 0);
    // i.width = "100px";
    // i.height = "20px";
    // document.getElementById("hub_iframe").appendChild(i);
}

if (window.addEventListener) {window.addEventListener("load", async_load, false);}
else if (window.attachEvent) {window.attachEvent("onload", async_load);}
else {window.onload = async_load;}

! function() {
    //封装方法，压缩之后减少文件大小
    function get_attribute(node, attr, default_value) {
        return node.getAttribute(attr) || default_value;
    }
    //封装方法，压缩之后减少文件大小
    function get_by_tagname(name) {
        return document.getElementsByTagName(name);
    }
    //获取配置参数
    function get_config_option() {
        var scripts = get_by_tagname("script"),
            script_len = scripts.length,
            script = scripts[script_len - 1]; //当前加载的script
        return {
            l: script_len, //长度，用于生成id用
            z: get_attribute(script, "zIndex", -1), //z-index
            o: get_attribute(script, "opacity", 0.5), //opacity
            c: get_attribute(script, "color", "0,0,0"), //color
            n: get_attribute(script, "count", 99) //count
        };
    }
    //设置canvas的高宽
    function set_canvas_size() {
        canvas_width = the_canvas.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
            canvas_height = the_canvas.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
    }

    //绘制过程
    function draw_canvas() {
        context.clearRect(0, 0, canvas_width, canvas_height);
        //随机的线条和当前位置联合数组
        var e, i, d, x_dist, y_dist, dist; //临时节点
        //遍历处理每一个点
        random_lines.forEach(function(r, idx) {
            r.x += r.xa,
                r.y += r.ya, //移动
                r.xa *= r.x > canvas_width || r.x < 0 ? -1 : 1,
                r.ya *= r.y > canvas_height || r.y < 0 ? -1 : 1, //碰到边界，反向反弹
                context.fillRect(r.x - 0.5, r.y - 0.5, 1, 1); //绘制一个宽高为1的点
            //从下一个点开始
            for (i = idx + 1; i < all_array.length; i++) {
                e = all_array[i];
                //不是当前点
                if (null !== e.x && null !== e.y) {
                    x_dist = r.x - e.x, //x轴距离 l
                        y_dist = r.y - e.y, //y轴距离 n
                        dist = x_dist * x_dist + y_dist * y_dist; //总距离, m
                    dist < e.max && (e === current_point && dist >= e.max / 2 && (r.x -= 0.03 * x_dist, r.y -= 0.03 * y_dist), //靠近的时候加速
                        d = (e.max - dist) / e.max,
                        context.beginPath(),
                        context.lineWidth = d / 2,
                        context.strokeStyle = "rgba(" + config.c + "," + (d + 0.2) + ")",
                        context.moveTo(r.x, r.y),
                        context.lineTo(e.x, e.y),
                        context.stroke());
                }
            }
        }), frame_func(draw_canvas);
    }
    //创建画布，并添加到body中
    var the_canvas = document.createElement("canvas"), //画布
        config = get_config_option(), //配置
        canvas_id = "c_n" + config.l, //canvas id
        context = the_canvas.getContext("2d"), canvas_width, canvas_height,
        frame_func = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function(func) {
            window.setTimeout(func, 1000 / 45);
        }, random = Math.random,
        current_point = {
            x: null, //当前鼠标x
            y: null, //当前鼠标y
            max: 20000
        },
        all_array;
    the_canvas.id = canvas_id;
    the_canvas.style.cssText = "position:fixed;top:0;left:0;z-index:" + config.z + ";opacity:" + config.o;
    get_by_tagname("body")[0].appendChild(the_canvas);
    //初始化画布大小

    set_canvas_size(), window.onresize = set_canvas_size;
    //当时鼠标位置存储，离开的时候，释放当前位置信息
    window.onmousemove = function(e) {
        e = e || window.event, current_point.x = e.clientX, current_point.y = e.clientY;
    }, window.onmouseout = function() {
        current_point.x = null, current_point.y = null;
    };
    //随机生成config.n条线位置信息
    for (var random_lines = [], i = 0; config.n > i; i++) {
        var x = random() * canvas_width, //随机位置
            y = random() * canvas_height,
            xa = 2 * random() - 1, //随机运动方向
            ya = 2 * random() - 1;
        random_lines.push({
            x: x,
            y: y,
            xa: xa,
            ya: ya,
            max: 6000 //沾附距离
        });
    }
    all_array = random_lines.concat([current_point]);
    //0.1秒后绘制
    setTimeout(function() {
        draw_canvas();
    }, 100);
}();