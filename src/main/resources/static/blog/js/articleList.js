var form;
var upload;
var articleTable;
var selectIds = '';
layui.use(['form', 'table', 'upload'], function(){
    articleTable = layui.table;
    form = layui.form;
    upload = layui.upload;
    //初始化数据列表
    articleTable.render({
        elem: '#articleListTable'
        ,cellMinWidth: 60
        ,height: 'full-125'
        ,toolbar: '#articleToolBar' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            layEvent: 'refresh'
            ,icon: 'layui-icon-refresh'
        }]
        ,url: '/system/article/page' //数据接口
        ,page: true //开启分页
        ,cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', hide: true , sort: true}
                ,{field: 'title', title: '标题', align: 'center'}
                ,{field: 'articleType', templet: typeTem, title: '文章类型', align: 'center'}
                ,{field: 'author', title: '作者',templet: nickNameTem, sort: true, align: 'center'}
                ,{field: 'publish', title: '发布状态',templet: '#switchPublish', sort: true, align: 'center'}
                ,{field: 'source', title: '来源', templet:sourceTem,  sort: true, align: 'center'}
                ,{field: 'viewTimes', title: '浏览次数', sort: true, align: 'center'}
                ,{field: 'collectTimes', title: '收藏次数', align: 'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#operateArticle', width:120, align: 'center'}
            ]
        ]
        ,parseData:function (res) {
            return {
                "code":res.code,
                "msg":res.msg,
                "count":res.module.total,
                "data":res.module.list
            };
        }
    });


  //监听发布状态操作
  form.on('switch(publishFilter)', function(obj){
    // layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
    if (obj.elem.checked == true) {
        publishArticle(this.value);
    }else {
      takeOffPublishArticle(this.value);
    }

  });


    //头工具栏事件
    articleTable.on('toolbar(articleFilter)', function(obj){
        var checkStatus = articleTable.checkStatus("articleListTable");
        switch(obj.event){
            case 'addArticle':
                addArticle();
                break;
            case 'mulDelete':
                var data = checkStatus.data;
                if (data.length == 0){
                    layer.msg("请选择文章",{time: 2000,offset:'66px'});
                }else {
                    var ids = '';
                    for (var i=0;i<data.length;i++){
                        ids += data[i].id +',';
                    }
                    deleteArticle(ids);
                }
                break;
          case 'siteTitlePage':
            var data = checkStatus.data;
            if (data.length == 0){
              selectIds = '';
              layer.msg("请选择文章",{time: 2000,offset:'66px'});
            }else {
              var ids = '';
              for (var i=0;i<data.length;i++){
                ids += data[i].id +',';
              }
              selectIds = ids;
              // siteTilePage(ids);
            }
            break;
            //自定义头工具栏右侧图标 - 刷新
            case 'refresh':
                articleTable.reload('articleListTable');
                break;
        }
    });

    //监听行工具事件
    articleTable.on('tool(articleFilter)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            deleteArticle(data.id);
        } else if(obj.event === 'edit'){
            updateArticle(data.id);
        }
    });

  //图片上传
  upload.render({
    elem: '#siteTitlePage' //绑定元素
    ,url: '/system/file/uploadImg' //上传接口
    ,before:function (obj) {
      layer.load();
    }
    ,done: function(res){
      if (selectIds==''){
        layer.close('loading');
        layer.msg("设置封面失败：未选择文章",{time: 2000,offset:'66px'});
      }else {
        siteTilePage(res.data[0],selectIds);
        layer.close('loading');
      }
      //上传完毕回调
    }
    ,error: function(){
      layer.close('loading');
      layer.msg("设置封面失败",{time: 2000,offset:'66px'});
      //请求异常回调
    }
  });
});

//展示文章类型
function typeTem(data) {
    return data.articleType.cName;
}

//展示作者
function nickNameTem(data){
  return data.originalAuthor == null || data.originalAuthor == "" ? data.author.nickName : data.originalAuthor;
}

//来源
function sourceTem(data){
  return data.source == 0 ? "原创" : "转载";
}

//新增
function addArticle() {
    $.ajax({
        method:"get",
        url: '/system/article/addUI',
        data:{"mode":'add'},
        async:false,
        success:function (res) {
            layer.open({
                type:1
                ,area:['560px','520px']
                ,title: '新增'
                ,content: res
                ,shade: [0.3, '#393D49']
                ,btn: ['提交', '取消']
                ,btn1: function(index, layero){
                    $("#articleForm").attr("action","/system/article/saveArticle").submit();
                },
                btn2: function(index, layero){
                    layer.closeAll();
                }
            })
        }
    });
}

//修改
function updateArticle(id) {
    $.ajax({
        method:"get",
        url: '/system/article/edit/'+id,
        async:false,
        data:{
            "id":id,
            "mode":"edit"
        },
        success:function (res) {
            layer.open({
                type:1
                ,area:['560px','520px']
                ,title: '修改'
                ,shade: [0.3, '#393D49']
                ,content: res
                ,btn: ['提交', '取消']
                ,btn1: function(index, layero){
                    $("#articleForm").attr("action","/system/article/updateArticle").submit();
                    articleTable.reload('articleListTable');
                },
                btn2: function(index, layero){
                    layer.closeAll();
                }
            })
        }
    });
}

//发布文章
function publishArticle(id) {
  layer.confirm('确认发布文章？', function(index){
    $.ajax({
      method:"patch",
      url: '/system/article/publishArticle',
      async:false,
      data:{
        "id":id
      },
      success:function (res) {
        layer.closeAll();
        layer.msg("发布成功",{time: 2000,offset:'66px'});
        articleTable.reload('articleListTable');
      }
    });
    layer.close(index);
  });
}

//下架文章
function takeOffPublishArticle(id) {
  layer.confirm('确认下架文章？', function(index){
    $.ajax({
      method:"patch",
      url: '/system/article/takeOffPublishArticle',
      async:false,
      data:{
        "id":id
      },
      success:function (res) {
        layer.closeAll();
        layer.msg("下架成功",{time: 2000,offset:'66px'});
        articleTable.reload('articleListTable');
      }
    });
    layer.close(index);
  });
}

//删除
function deleteArticle(ids) {
    layer.confirm('确认删除文章？', function(index){
        $.ajax({
            method:"delete",
            url: '/system/article/deleteArticlesByIds',
            async:false,
            data:{
                "ids":ids
            },
            success:function (res) {
                layer.closeAll();
                layer.msg("删除成功",{time: 2000,offset:'66px'});
                articleTable.reload('articleListTable');
            }
        });
        layer.close(index);
    });
}

//设置封面
function siteTilePage(titlePage,ids) {
    $.ajax({
      method:"post",
      url: '/system/article/siteTitlePage',
      data:{
        "titlePage":titlePage,
        "ids":ids
      },
      success:function (res) {
        layer.closeAll();
        layer.msg("设置成功",{time: 2000,offset:'66px'});
      },
      error:function () {
        layer.closeAll('loading');
        layer.msg("设置封面失败",{time: 2000,offset:'66px'});
      }
    });
}

