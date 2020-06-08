<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="js/jquery.min.js"></script>

</head>
<SCRIPT language=javascript>
<!--
var displayBar=true;
function switchBar(obj){
	if (displayBar)
	{
		parent.frame.cols="0,*";
		displayBar=false;
		obj.value="打开左边管理菜单";
	}
	else{
		parent.frame.cols="195,*";
		displayBar=true;
		obj.value="关闭左边管理菜单";
	}
}

function fullmenu(url){
	if (url==null) {url = "admin_left.asp";}
	parent.leftFrame.location = url;
}

//-->
</SCRIPT>


 
<script type="text/javascript">
    var req;
   
    function shanxuan(){//当第一个下拉框的选项发生改变时调用该函数
    	var kechengid = encodeURI(encodeURI($("#kechengid").val()));
		var leixingid = encodeURI(encodeURI($("#leixingid").val()));
		var nanduid = encodeURI(encodeURI($("#nanduid").val()));
		
		

      var now = new Date();
      
      var url = "shijuanitemmethod!shanxuan?kechengid="+kechengid+"&leixingid="+leixingid+"&nanduid="+nanduid+"&t="+now.getTime();
      
      if(window.XMLHttpRequest){
        req = new XMLHttpRequest();
      }else if(window.ActiveXObject){
        req = new ActiveXObject("Microsoft.XMLHTTP");
      }
      if(req){
        //指定回调函数为callback
      	req.onreadystatechange = callback;
        req.open("GET",url,true);
        req.send(null);
      }
    }
    //回调函数
    function callback(){
      if(req.readyState ==4){
        if(req.status ==200){

        document.getElementById('tablevaule').innerHTML=req.responseText;
 
          //parseMessage();//解析XML文档
        }else{
          alert("不能得到描述信息:" + req.statusText);
        }
      }
    }
       


</script>
 

<body>
<input onClick="switchBar(this)" type="button" value="关闭左边管理菜单" name="SubmitBtn" />

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    

    
    <tr>
      <td class="td_bg"  height="23">难度:</td>
      <td class="td_bg" >
      				 <select name="nandu" id="nanduid">
      				 <option  value="">所有选项</option>
   					 <option  value="简单" >简单</option>
   					 <option  value="中等" >中等</option>
    				 <option  value="复杂" >复杂</option>
     				 </select>
      </td>
     
    </tr>
    
     <input type="hidden" id="kechengid" value="${bean.kecheng.id }">
    
     <tr>
      <td class="td_bg"  height="23">题型:</td>
      <td class="td_bg" >
     		
     		<select name="leixing"  id="leixingid">
				<option value="">所有选项</option>
				<option value="填空题">填空题</option>
				<option value="问答题">问答题</option>
				<option value="选择题">选择题</option>
				<option value="判断题">判断题</option>
				</select>
     		
      </td>
     
    </tr>
    
    
    
   
    
    
    
    <tr>
      <td class="td_bg"  height="23">操作</td>
      <td class="td_bg" >
      <input type="button" value="筛选试题" onclick="shanxuan()" style="width: 100px" /> 

      &nbsp; &nbsp; &nbsp; &nbsp;
      
      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" />
      </td>
     
    </tr>

</tbody>
</table>


<form method="post" action="shijuanitemmethod!shijuanitemadd2" >
<span style="color: red;">
注意：打钩表示选择该题目，选择该题目后请填写分值框，否则会报错
</span>

<input type="hidden" name="shijuanid" value="${shijuanid }"/> 
 <div id="tablevaule">
			
				</div>
<input type="submit" value="提&nbsp;交" size="20" />				
				
</form>	

</body>
</html>

