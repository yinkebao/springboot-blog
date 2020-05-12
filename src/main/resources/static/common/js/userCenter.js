var createDate = new Date($("#user_createDate").val()).getTime();
var nowDate = new Date().getTime();
var theTime = parseInt((nowDate-createDate)/1000);
setInterval(function () {
  siteRunningTime(theTime);
  theTime++;
},1000);

$(function () {
  setArticleList();
  articleSourceEcharts();
  articleTagsEcharts();
  articleTypeEcharts();
  initArticleSwiper();
  countMyArticleInfo();
});

//统计我的文章信息
function countMyArticleInfo() {
  $.ajax({
    async:false,
    url:'/system/article/countMyArticleInfo',
    data:{
      'userId':$("#currentUserId").val()
    },
    success:function (data) {
      $("#articleTotal").html(data.module.articleNum == null ? 0 : data.module.articleNum);
      $("#articleViewTimes").val(data.module.viewTimes == null?0:data.module.viewTimes);
      $("#articleCollectTimes").val(data.module.collectTimes == null?0:data.module.collectTimes);
    }
  })
}

//初始化swiper
function initArticleSwiper() {
  var mySwiper = new Swiper('.swiper-articleList', {
    direction: 'vertical',    // 垂直切换选项 horizontal（横向） vertical（垂直）
    loop: true,                 // 循环模式选项
    grabCursor: true,           //鼠标放入变成手型
    effect: "slide",             //切换效果 'slide'（普通切换、默认）,"fade"（淡入）"cube"（方块）"coverflow"（3d流）"flip"（3d翻转）
    mousewheelControl: true ,  //鼠标滑动控制
    slidesPerView : 5,
    spaceBetween: 8,
    slidesPerGroup : 1,
    speed: 1000,
    autoplay: {
      delay: 0,
      stopOnLastSlide: false,
      disableOnInteraction: false
    },
    pagination: {
      el: '.swiper-articleList .swiper-pagination',
      clickable: true,
      dynamicBullets: true
    },
    navigation: {
      nextEl: '.swiper-articleList .swiper-button-next',
      prevEl: '.swiper-articleList .swiper-button-prev'
    },
    roundLengths: true,
    freeMode : true,
    freeModeMomentumRatio : 0
  });

  // 鼠标移入停止自动滚动
  $('.swiper-articleList .swiper-slide').mouseenter(function() {
    mySwiper.autoplay.stop();
  });
  // 鼠标移出开始自动滚动
  $('.swiper-articleList .swiper-slide').mouseleave(function() {
    mySwiper.autoplay.start();
  });
}

//设置轮询内容
function setArticleList(){
  $.ajax({
    async:false,
    data:{
      "userId":$("#currentUserId").val()
    },
    url:"/system/article/listMyArticles",
    success:function (data) {
      data.module.forEach(function (value) {
        var str = '';
        str += '<div class="swiper-slide">';
        str += '<span class="label label-success" style="color: #3a99a6; background-color: #b1d5f7">';
        if (value.source == 0){
          str += '原创';
        } else {
          str += '转载';
        }
        str += '</span>&emsp;<a href="/system/article/viewArticle?id='+value.id+'" target="_blank"><span>' + value.title + '</span></a><span class="slide_date">'+ value.publicDateStr +'</span></div>';
        $("#myArticleList").append(str);
      });
    }
  });
}

//标签统计
function articleTagsEcharts() {
  var articleTags = echarts.init(document.getElementById('article_tags'));
  var plantCap = [{
    name: '协同过滤',
    value: '2'
  }, {
    name: '推荐',
    value: '2'
  }, {
    name: '数据结构',
    value: '3'
  }, {
    name: 'java',
    value: '1'
  }, {
    name: 'HTML',
    value: '6'
  }, {
    name: 'bootstrap',
    value: '4'
  }, {
    name: 'ES',
    value: '2'
  }, {
    name: '数据库',
    value: '6'
  }, {
    name: '前端',
    value: '2'
  }, {
    name: 'oracle',
    value: '7'
  }];

  var datalist = [{
    offset: [56, 48],
    symbolSize: 99,
    opacity: .87,
    color: '#f4b2e0'
  }, {
    offset: [35, 70],
    symbolSize: 95,
    opacity: .88,
    color: '#7aabe2'
  }, {
    offset: [20, 43],
    symbolSize: 90,
    opacity: .84,
    color: '#ff7123'
  }, {
    offset: [83, 30],
    symbolSize: 90,
    opacity: .70,
    color: '#ffca84'
  }, {
    offset: [36, 20],
    symbolSize: 65,
    opacity: .75,
    color: '#9caace'
  }, {
    offset: [64, 10],
    symbolSize: 70,
    opacity: .55,
    color: '#b3a372'
  }, {
    offset: [75, 66],
    symbolSize: 60,
    opacity: .88,
    color: '#43b6c9'
  }, {
    offset: [88, 62],
    symbolSize: 50,
    opacity: .56,
    color: '#f1c868'
  }, {
    offset: [69, 80],
    symbolSize: 60,
    opacity: .60,
    color: '#43b6c9'
  }, {
    offset: [88, 78],
    symbolSize: 60,
    opacity: .54,
    color: '#a9c9a5'
  }, {
    offset: [155, 72],
    symbolSize: 60,
    opacity: .56,
    color: '#c9b3ab'
  }, {
    offset: [78, 70],
    symbolSize: 120,
    opacity: .56,
    color: '#43b6c9'
  }];
  var datas = [];
  for (var i = 0; i < plantCap.length; i++) {
    var item = plantCap[i];
    var itemToStyle = datalist[i];
    datas.push({
      name: item.value + '\n' + item.name,
      value: itemToStyle.offset,
      symbolSize: itemToStyle.symbolSize,
      label: {
        normal: {
          textStyle: {
            fontSize: 11
          }
        }
      },
      itemStyle: {
        normal: {
          color: itemToStyle.color,
          opacity: itemToStyle.opacity
        }
      }
    })
  }
  var option = {
    "grid": {
      left: '2%',
      right: '10%',
      "show": false,
      "top": 10,
      "bottom": 10
    },
    title: {
      text: '标签统计',
      x:'center',
      y:'10px',
      backgroundColor: 'rgba(0,0,0,0)',
      textStyle: {//主标题文本样式{"fontSize": 18,"fontWeight": "bolder","color": "#333"}
        fontFamily: '宋体, Verdana, sans...',
        fontSize: 15,
        fontStyle: 'normal',
        fontWeight: 'normal',
        color: '#1a6681'
      }
    },
    "xAxis": [{
      "gridIndex": 0,
      "type": "value",
      "show": false,
      "min": 0,
      "max": 100,
      "nameLocation": "middle",
      "nameGap": 5
    }],
    "yAxis": [{
      "gridIndex": 0,
      "min": 0,
      "show": false,
      "max": 100,
      "nameLocation": "middle",
      "nameGap": 30
    }],
    "series": [{
      "type": "scatter",
      "symbol": "circle",
      "symbolSize": 120,
      "label": {
        "normal": {
          "show": true,
          "formatter": "{b}",
          "color": "#fff",
          "textStyle": {
            "fontSize": "20"
          }
        }
      },
      "itemStyle": {
        "normal": {
          "color": "#00acea"
        }
      },
      "data": datas
    }]
  };
  articleTags.setOption(option);
}

//文章来源统计
function articleSourceEcharts() {
  var articleSources = echarts.init(document.getElementById('article_sources'));
  var option= {
    color:["#90DDE0","#53C1FA","#5239ff"],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'none'
      }
    },
    grid: {
      left: '5%',
      right: '10%',
      bottom: '6%',
      containLabel: true
    },
    title: {
      text: '文章来源统计',
      x:'center',
      y:'10px',
      backgroundColor: 'rgba(0,0,0,0)',
      textStyle: {//主标题文本样式{"fontSize": 18,"fontWeight": "bolder","color": "#333"}
        fontFamily: '宋体, Verdana, sans...',
        fontSize: 15,
        fontStyle: 'normal',
        fontWeight: 'normal',
        color: '#1a6681'
      }
    },
    xAxis: {
      axisLabel: {
        textStyle:{
          color:'#5999e8'  //坐标的字体颜色
        }
      },
      axisLine:{
        show: true, //隐藏坐标轴
        lineStyle:{
          color:'#00eeff' //坐标轴的颜色
        }
      },
      axisTick:{
        show:false //隐藏坐标轴的刻度
      },
      data: ['原创', '转载']
    },
    yAxis: {
      splitLine:{
        show:false
      },
      type: 'value',
      axisLabel: {
        textStyle:{
          color:'#5999e8'  //坐标的字体颜色
        }
      },
      axisLine:{
        show: true, //隐藏坐标轴
        lineStyle:{
          color:'#00eeff' //坐标轴的颜色
        }
      },
      axisTick:{
        show:false //隐藏坐标轴的刻度
      }
    },
    series: [{
      name: '文章',
      type: 'pictorialBar',
      barCategoryGap: '0%',
      label: {
        normal: {
          show: true,
          position: 'top'
        }
      },
      // symbol: 'path://M0,10 L10,10 L5,0 L0,10 z',
      symbol: 'path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z',
      itemStyle: {
        normal: {
          opacity: 0.5,
          color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
            offset: 0,
            color: "#d1eef3" // 0% 处的颜色
          }, {
            offset: 0.6,
            color: "#90DDE0" // 60% 处的颜色
          }, {
            offset: 1,
            color: "#5C98DC" // 100% 处的颜色
          }], false)
        },
        emphasis: {
          opacity: 1
        }
      },
      data: [5, 9],
      z: 10
    }]
  };
  articleSources.setOption(option);
}

//文章类型
function articleTypeEcharts() {
  var articleSources = echarts.init(document.getElementById('article_types'));
  var option = {
    color:["#8398ce","#53C1FA","#5239ff"],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'none'
      }
    },
    grid: {
      left: '5%',
      right: '10%',
      bottom: '6%',
      containLabel: true
    },
    title: {
      text: '文章类型统计',
      x:'center',
      y:'10px',
      backgroundColor: 'rgba(0,0,0,0)',
      textStyle: {//主标题文本样式{"fontSize": 18,"fontWeight": "bolder","color": "#333"}
        fontFamily: '宋体, Verdana, sans...',
        fontSize: 15,
        fontStyle: 'normal',
        fontWeight: 'normal',
        color: '#1a6681'
      }
    },
    xAxis: {
      axisLabel: {
        rotate:28,
        textStyle:{
          color:'#5999e8'  //坐标的字体颜色
        }
      },
      axisLine:{
        show: true, //隐藏坐标轴
        lineStyle:{
          color:'#00eeff' //坐标轴的颜色
        }
      },
      axisTick:{
        show:false //隐藏坐标轴的刻度
      },
      type: 'category',
      data: ['springBoot', 'java', 'python', 'c++', 'sql', 'oracle']
    },
    yAxis: {
      splitLine:{
        show:false
      },
      type: 'value',
      axisLabel: {
        textStyle:{
          color:'#5999e8'  //坐标的字体颜色
        }
      },
      axisLine:{
        show: true, //隐藏坐标轴
        lineStyle:{
          color:'#00eeff' //坐标轴的颜色
        }
      },
      axisTick:{
        show:false //隐藏坐标轴的刻度
      }
    },
    series: [{
      data: [2, 3, 2, 1, 1, 1],
      type: 'bar',
      barWidth:'18',
      itemStyle: {
        normal: {
          opacity: 0.5,
          color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
            offset: 0,
            color: "#afe0f3" // 0% 处的颜色
          }, {
            offset: 0.6,
            color: "#90DDE0" // 60% 处的颜色
          }, {
            offset: 1,
            color: "#5C98DC" // 100% 处的颜色
          }], false)
        },
        emphasis: {
          opacity: 1
        }
      },
      barCategoryGap:'28%',
      showBackground: true,
      backgroundStyle: {
        color: '#f6ffff'
      }
    }]
  };
  articleSources.setOption(option);
}

//计时器
function siteRunningTime(time) {
  var theTime;
  var strTime = "";
  if (time >= 86400){
    theTime = parseInt(time/86400);
    strTime += theTime + "天";
    time -= theTime*86400;
  }
  if (time >= 3600){
    theTime = parseInt(time/3600);
    strTime += theTime + "时";
    time -= theTime*3600;
  }
  if (time >= 60){
    theTime = parseInt(time/60);
    strTime += theTime + "分";
    time -= theTime*60;
  }
  strTime += time + "秒";
  $('.createDate').html(strTime);
}