<html>
<body>
<h2>Hello World!</h2>
<script src="/js/aj1.js"></script>
</body>
<form action="/listPackage" method="post">
    姓名1：<input type="text" name="name"/><br>
    年龄1：<input type="text" name="age"/><br>
    邮箱1：<input type="text" name="email"/>
    姓名2：<input type="text" name="name"/><br>
    年龄2：<input type="text" name="age"/><br>
    邮箱2：<input type="text" name="email"/><br>
    <input type="submit" id="testList" value="提交"><br>
    <input type="button" value="ajaxtest" onclick="ajaxtest()">
</form>
<script>
var req = [{name:wang,age:12,email:"163.com"},{name:ccc,age:1,email:"111.com"}]
function dopost(req,url) {
    ajax({
        type:"POST",
        url:url,
        dataType:"json",
        data:req,
        beforeSend:function(){
            //some js code
        },
        success:function(msg){
            console.log(msg)
        },
        error:function(){
            console.log("error")
        }
    })
}

function ajaxtest() {
    var url ="localhost:8080/list"
    dopost(req,url);
}

</script>
</html>
