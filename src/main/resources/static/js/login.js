
//定义HTTP连接对象
var xmlHttp;

//实例化HTTP连接对象
function createXmlHttpRequest() {
    if(window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    } else if(window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
}


//发起登录请求
function login() {
    createXmlHttpRequest();
    var name = document.getElementById("username").value;
    var password = document.getElementById("userpassword").value;
    console.log(name,password)
    var url = "checkLogin.do?emp_id="+name+"&emp_password="+password;
    //不知道这里的代码为啥要这样写。。。。。如果不这样写 mlHttp.readyState 会一直=1 而且返回的json不能被接收 草..........
    //console.log(url);
    xmlHttp.open("GET",url,true);
    xmlHttp.onreadystatechange = handleResult;
    xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    xmlHttp.send(null);

}

//处理服务器返回的结果/更新页面
function handleResult() {
    var name = document.getElementById("username").value;
    console.log("进入这里handleresult");
    console.log(xmlHttp.readyState, xmlHttp.status);
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        console.log("成功进入这里");
        console.log(typeof xmlHttp.responseText);//服务器响应的原始数据
        //console.log(typeof eval('('+xmlHttp.responseText+')'));//不推荐使用
        console.log(typeof JSON.parse(xmlHttp.responseText));
        rel = JSON.parse(xmlHttp.responseText);
        console.log(rel.res);
        if(rel.res=="enable") {
          //alert("登录成功！");
        //页面跳转
           window.location.href='main.html?emp_id='+name;
           }else{
            alert("登录失败，用户名或密码错误");
        }

    }
}
