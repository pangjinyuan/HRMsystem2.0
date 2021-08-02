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
function main() {
    var emp_id=window.location.search;
    console.log(emp_id);
    createXmlHttpRequest();
    var url = "Main.do"+emp_id;
    //不知道这里的代码为啥要这样写。。。。。如果不这样写 mlHttp.readyState 会一直=1 而且返回的json不能被接收 草..........
    //console.log(url);
    xmlHttp.open("GET",url,true);
    xmlHttp.onreadystatechange = handleResult;
    xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    xmlHttp.send(null);

}

//处理服务器返回的结果/更新页面
function handleResult() {
    console.log("进入这里handleresult");
    console.log(xmlHttp.readyState, xmlHttp.status);
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        console.log("成功进入这里");
        console.log(typeof xmlHttp.responseText);//服务器响应的原始数据
        //console.log(typeof eval('('+xmlHttp.responseText+')'));//不推荐使用
        console.log(typeof JSON.parse(xmlHttp.responseText));
        rel = JSON.parse(xmlHttp.responseText);
        console.log(rel.menutree);

        var temp=1;
        for( i in rel.menutree){
            var fatherli;
            var sonul;
            if(temp==1){
                $("#nav li a ").append("<cite style=\"inline;\">"+i+"</cite>");
            }
            else{
                fatherli=$("<li class ><a  href=\"javascript:;\" class><cite >"+i+"</cite></a>"+"</li>");
                // sondiv=$("<div></div>")
                sonul=$("<ul class='sub-menu' style></ul>");
            }

            console.log("这是父节点:"+i);
            for (j in rel.menutree[i]){
                var sonli;
                //这里onclick可以将地址发送过去
                if(temp==1){
                    var str=rel.menutree[i][j].power_name.toString();
                    var urls=rel.menutree[i][j].url;
                    $("#nav li ul").append("<li><a onclick='pagejump(\""+urls+"\")' class><cite style='inline;'>"+str+"</cite></a></li>")
                }
                else{
                    var urls=rel.menutree[i][j].url.toString();
                    var str=rel.menutree[i][j].power_name.toString();
                    console.log("这是子节点链接:"+urls);
                    sonli=$("<li><a onclick='pagejump(\""+urls+"\")' class><cite>"+str+"</cite></a> </li>");
                    //这里需要转义字符
                    sonul.append(sonli);
                    console.log("这是子节点:"+rel.menutree[i][j].power_name.toString());
                }

            }
           // sondiv.append(sonul);
            if(temp!=1){
                fatherli.append(sonul);
                $("#nav").append(fatherli);
            }
            temp=temp+1;

        }
    }
}

//这个函数不要删

function pagejump(urls) {
        document.getElementById("MyApplicationPage").src=urls;
}