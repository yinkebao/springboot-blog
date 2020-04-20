var content = $("#inputContent").val();
$(".article_content").append(content);

$(function () {
  showComment();
  view();
});

function view(){
  var articleId = $("#comment_articleId").val();
  $.ajax({
    async:false,
    url:'/system/articleView/isViewed',
    data:{
      "userId":$("#currentUserId").val(),
      "articleId":articleId
    },
    success:function (data) {
      if (!data.module){
        viewed(articleId);
      }
    }
  })
}

//阅读
function viewed(articleId){
  $.ajax({
    async:false,
    method:'post',
    url:'/system/articleView/view',
    data:{
      "userId":$("#currentUserId").val(),
      "articleId":articleId
    },
    success:function (data) {
      
    }
  })
}

$("#article_comment").click(function () {
  var comment = $("#comment_content").val();
  if (comment == null || comment == ""){
    alert("请输入评论内容");
    return;
  }
  $.ajax({
    method:"POST",
    data:{
      "content":comment,
      "articleId":$("#comment_articleId").val(),
      "userId":$("#currentUserId").val()
    },
    url:"/system/comment/saveComment",
    async: true,
    success: function (date) {
      $("#comment_content").val(null);
      //加载评论
      showComment();
    }
  })
});

//展示评论
function showComment() {
  $.ajax({
    method:"post",
    data:{
      "articleId":$("#comment_articleId").val()
    },
    url:"/system/comment/listArticleComments",
    success:function (data) {
      document.getElementById("comment_ul").innerHTML="";
      var index = 1;
      $("#comment_num").text(data.module.length);
      data.module.forEach(function (comment) {
        appendComment(comment,index);
        index ++;
      })
    }
  })
}

//动态添加评论
function appendComment(comment,index) {
  var str = '<div class="comment_li" id="comment_li_'+index+'"><div class="comment_title">';
  str += '<span class="comment_name">'+comment.user.nickName+'</span>';
  str += '<span class="comment_index">#'+index+'楼</span>';
  str += '<span class="comment_time">'+comment.createDateStr+'</span>';
  str += '</div>';
  str += '<div class="comment_content">';
  str += comment.content;
  str += '</div>';
  str += '<div class="comment_info">';
  str += '<a><span class="layui-icon layui-icon-praise"></span></a> <a class="comment_zan">'+comment.likeNum == null ? 0: comment.likeNum+'人赞 </a>';
  if (comment.childes.length > 0 ){
    str += '<a onclick="addComment(\''+comment.id+'\',\'comment_reply_list_'+index+'\','+index+',\''+comment.user.id+'\')">';
  }else {
    str += '<a onclick="addComment(\''+comment.id+'\',\'comment_li_'+index+'\','+index+',\''+comment.user.id+'\')">';
  }
  str += '<span class="layui-icon layui-icon-reply-fill" style="color: rgba(91,141,107,0.48);"></span>';
  str += '<a class="comment_zan">回复</a></div></a>';
  if (comment.childes.length > 0 ){
    str += '<div class="comment_reply_list" id="comment_reply_list_'+index+'">';
    comment.childes.forEach(function (child) {
      str += '<div class="comment_reply_li">';
      str += '<div class="comment_reply">';
      str += '<span class="replay_name">'+child.user.nickName+':  &nbsp;@'+child.commentedNickName+'</span>';
      str += '<span class="replay_content"> '+child.content+'</span></div>';
      str += '<div class="reply_time"><span class="">'+child.createDateStr+'</span>';
      str += '<a onclick="addComment(\''+comment.id+'\',\'comment_reply_list_'+index+'\','+index+',\''+child.user.id+'\')"><span class="layui-icon layui-icon-reply-fill" style="color: rgba(91,141,107,0.48);"></span>';
      str += ' 回复</div><hr/></div></a>';
    });
    str += '<div class="new_comment_reply">';
    str += '<span class="glyphicon glyphicon-edit"></span><a onclick="addComment(\''+comment.id+'\',\'comment_reply_list_'+index+'\','+index+',\''+comment.user.id+'\')"> 添加新评论</a></div>';
  }
  str += '</div><hr style="border-top: 1px dashed #a4c6d3"/>';
  $("#comment_ul").append(str);
}

//添加评论
function addComment(commentId,nodeId,index,commentedUserId) {
  var str = '<div class="add_comment" id="add_comment_'+index+'">';
  str += '<textarea id="add_comment_content_'+index+'" placeholder="写下你的评论..."></textarea>';
  str += '<div class="comment_operate">';
  str += '<a class="apply_comment" onclick="replayComment(\'add_comment_content_'+index+'\','+commentId+',\'add_comment_'+index+'\',\''+commentedUserId+'\')">确认</a>';
  str += '<a class="cancel_comment" onclick="closeComment(\'add_comment_'+index+'\')">取消</a>';
  str += '</div></div>';
  $("#"+nodeId).append(str);
}

//回复评论
function replayComment(contentId,commentId,textareaId,commentedUserId) {
  var comment = $("#"+contentId).val();
  if (comment == null || comment == ""){
    alert("请输入评论内容");
    return;
  }
  $.ajax({
    method:"POST",
    data:{
      "content":comment,
      "parentId":commentId,
      "commentedUserId":commentedUserId,
      "articleId":$("#comment_articleId").val(),
      "userId":$("#currentUserId").val()
    },
    url:"/system/comment/saveComment",
    async: true,
    success: function (date) {
      $("#"+textareaId.toString()).remove();
      //加载评论
      showComment();
    }
  })
}

//关闭评论框
function closeComment(textareaId) {
  $("#"+textareaId.toString()).remove();
}