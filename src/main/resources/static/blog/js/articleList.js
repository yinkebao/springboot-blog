var form;
var articleTable;
layui.use(['form',  'table'], function(){
    articleTable = layui.table;
    form = layui.form;
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
                ,{field: 'author', title: '作者', sort: true, align: 'center'}
                ,{field: 'source', title: '来源',  sort: true, align: 'center'}
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
});

//展示文章类型
function typeTem(data) {
    return data.articleType.cName;
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
