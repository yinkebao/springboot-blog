$(function () {

});

//退出登录
function outLogin(userName) {
  $.ajax({
    data:{
      "userName": userName
    },
    url: "/user/outLogin",
    success:function () {
      location.reload();
    }
  })
}