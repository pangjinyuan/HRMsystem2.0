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


//发起查询请求
function role() {
    var post_name=document.getElementById("selpost_name").value;
    if(post_name==null){
        window.alert("选择值为空，请重新操作")
    }else{
        console.log(post_name);
        createXmlHttpRequest();
        var url = "/rolepower";
        //不知道这里的代码为啥要这样写。。。。。如果不这样写 mlHttp.readyState 会一直=1 而且返回的json不能被接收 草..........
        //console.log(url);
        xmlHttp.open("GET",url,true);
        xmlHttp.onreadystatechange = handleResult;
        xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
        xmlHttp.send(null);

    }
}

//处理服务器返回的结果/更新页面
function handleResult() {
    console.log("进入这里handleresult");
    console.log(xmlHttp.readyState, xmlHttp.status);
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        console.log("成功进入这里");
        console.log(xmlHttp.responseText);//服务器响应的原始数据
        //console.log(typeof eval('('+xmlHttp.responseText+')'));//不推荐使用
        console.log(typeof JSON.parse(xmlHttp.responseText));
        rel = JSON.parse(xmlHttp.responseText);
        console.log("KKKKKKKKKKKKKKKKKKKK: "+rel);

        var nottableId = document.getElementById("notowned");
        for(var i=0;i<nottableId.rows.length;i++)
        {
            notr=$("<tr></tr>")
            for(j in rel){
                if(rel[j].res==1){
                    if(rel[j].power.father_name==nottableId.rows[i].cells[0].innerHTML){
                        notr.append("<input type='checkbox' title='"+rel[j].power.power_name+"'value='"+rel[j].power.power_name+"'>")
                    }
                }

            }
            console.log("waawaawawawawawawawawa")
            $("#notowned").find("tr:eq("+ i +")").append(notr);
        }
       /* for(i in rel){
            if(rel[i].res==1){
                //如果他等于1；就是这个人已经拥有的权限，就将其加入已经拥有的权限里的某列
                for(var i=0;i<nottableId.rows.length;i++)
                {
                    //console.log(nottableId.rows[i].cells[0].innerHTML);
                    if(rel[i].power.father_name==nottableId.rows[i].cells[0].innerHTML){
                        $("#notowned").rows[i].append("<input type="checkbox" name="" title="写作" checked>")
                    }
                }


            }else{
                //如果它等于0，就是这个人未拥有的权限
            }
            console.log(rel[i].power.power_name);
            console.log(rel[i].res);
        }

        */

        $("#notowned").load(i)
    }
}


function refreshComment()
{
    var post_name = document.getElementById("postoption").value;
    console.log(post_name);
    var url = "/rolepower";
    $.ajax({
        url: url,
        type: 'POST',
        data: {postoption: post_name},
        success: function (data) {
            $("#id_comment_refresh").html(data);
        }

    })
}