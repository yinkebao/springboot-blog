function f() {
    var userName = localStorage.getItem("userName");
    console.log(userName);
}

$(document).ready(function () {
   f();
});