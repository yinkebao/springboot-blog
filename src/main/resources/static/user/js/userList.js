layui.use('element', function(){
    var element = layui.element;
});

var form;
var userTable;

//加载列表
layui.use(['form',  'table'], function(){
    userTable = layui.table;
    form = layui.form;
    userTable.render({
        elem: '#userListTable'
        ,cellMinWidth: 60
        ,height: 'full-125'
        ,toolbar: '#userToolBar' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            layEvent: 'refresh'
            ,icon: 'layui-icon-refresh'
        }]
        ,url: '/system/user/page' //数据接口
        ,page: true //开启分页
        ,cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', hide: true , sort: true}
                ,{field: 'userName', title: '用户名', align: 'center'}
                ,{field: 'nickName', title: '昵称', sort: true, align: 'center'}
                ,{field: 'birthDay', title: '生日' , sort: true, align: 'center'}
                ,{field: 'phone', title: '手机号', align: 'center'}
                ,{field: 'email', title: '邮箱', minWidth: 80, align: 'center'}
                ,{field: 'lastLoginTime', title: '最后登录时间',  sort: true, align: 'center'}
                ,{field: 'admin', title: '是否管理员', templet: adminTem,  sort: true, align: 'center'}
                ,{field: 'shutDown', title: '是否启用', align: 'center'}
                ,{field: 'lock', title: '是否锁定', sort: true, align: 'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#operateUser', width:150, align: 'center'}
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
    userTable.on('toolbar(userFilter)', function(obj){
        var checkStatus = userTable.checkStatus("userListTable");
        switch(obj.event){
            case 'addUser':
                addUser();
                break;
            case 'mulDelete':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;

            //自定义头工具栏右侧图标 - 刷新
            case 'refresh':
                userTable.reload('userListTable');
                break;
        }
    });

    //监听行工具事件
    userTable.on('tool(userFilter)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确认删除？', function(index){
                obj.del();
                deleteUser(data.id);
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            updateUser(data.id);
        }
    });
});

//新增
function addUser() {
    $.ajax({
        method:"get",
        url: '/system/user/addUI',
        data:{"mode":'add'},
        async:false,
        success:function (res) {
            layer.open({
                type:1
                ,area:['500px','460px']
                ,title: '新增'
                ,content: res
                ,shade: [0.3, '#393D49']
                ,btn: ['提交', '取消']
                ,btn1: function(index, layero){
                    $("#userForm").attr("action","/system/user/saveUser").submit();
                    userTable.reload('userListTable');
                },
                btn2: function(index, layero){
                    layer.closeAll();
                }
            })
        }
    });
}

//修改
function updateUser(id) {
    $.ajax({
        method:"get",
        url: '/system/user/edit/'+id,
        async:false,
        data:{
            "id":id,
            "mode":"edit"
        },
        success:function (res) {
            layer.open({
                type:1
                ,area:['500px','300px']
                ,title: '修改'
                ,shade: [0.3, '#393D49']
                ,content: res
                ,btn: ['提交', '取消']
                ,btn1: function(index, layero){
                    $("#userForm").attr("action","/system/user/updateUser").submit();
                    userTable.reload('userListTable');
                },
                btn2: function(index, layero){
                    layer.closeAll();
                }
            })
        }
    });
}

//删除
function deleteUser(ids) {
    $.ajax({
        method:"delete",
        url: '/system/user/deleteUsersByIds',
        async:false,
        data:{
            "ids":ids
        },
        success:function (res) {
            layer.closeAll();
            layer.msg("删除成功",{time: 2000,offset:'66px'});
        }
    });
}

//设置是否管理员
function adminTem(d) {
    return d.admin == true?'是':'否';
}

//获取选择的数据
//返回的数据格式：{data:[],isAll}
//其中isAll：true/false
function getCheckData(tableName) {
    var checkStatus = userTable.checkStatus(tableName);
    return checkStatus.data;
}

//获取选中数目
function getCheckLength(tableName) {
    return getCheckData(tableName).length;
}

//验证是否全选 0全选1未全选
function checkIsAll(tableName) {
    return getCheckData(tableName).isAll?0:1;
}