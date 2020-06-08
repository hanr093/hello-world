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


 
<script language="javascript" type="text/javascript">

function checkform()
{
	 
	

	 if (document.getElementById('shuomingid').value=="")
	{
		alert("资源说明不能为空");
		return false;
	}
	
	//验证文件的后缀名
	var b= document.getElementById("upload");

	if(b.value==""){
	
	alert('请选择上传的文件！');
	return false;
	}
	
	var FileName = b.value;
	
	var extension=new String (FileName.substring(FileName.lastIndexOf(".")+1,FileName.length));

	if(!(extension=="xls" || extension=="doc" || extension=="jpg" )){
		alert('必须上传xls或者doc或者jpg格式的文件');
		return false;
	
	}
	
	
	document.getElementById("filenameid").value= document.getElementById("upload").value;
	
	
	return true;
	
}


</script>
 

<body>
<input onClick="switchBar(this)" type="button" value="关闭左边管理菜单" name="SubmitBtn" />

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    <form method="post"   action="${url }" onsubmit="return checkform()" enctype="multipart/form-data" >
    
    <tr>
      <td class="td_bg"  height="23">所属课程</td>
      <td class="td_bg" >
      	
      	<select name="kechengid">
      	
      	<c:forEach items="${kechenglist}"  var="kecheng">
      	<option value="${kecheng.id }">${kecheng.mingchen }</option>
      	</c:forEach>
      	</select>
      	
      </td>
     
    </tr>
    
    <tr>
      <td class="td_bg"  height="23">资料说明</td>
      <td class="td_bg" >
      <input type="text" name="shuoming" id="shuomingid"   size="50"/>
      </td>
     
    </tr>
    
    <tr>
      <td class="td_bg"  height="23">上传资料</td>
      <td class="td_bg" >
      <input name="uploadfile"  type="file"  id="upload"  size="50"/><input type="hidden" name="filename" id="filenameid">
      </td>
     
    </tr>
    
    
    
    <tr>
      <td class="td_bg"  height="23">操作</td>
      <td class="td_bg" >
      <input type="submit" value="提交"/>
      &nbsp; &nbsp; &nbsp; &nbsp;
      
      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" />
      </td>
     
    </tr>
    </from>
</tbody>
</table>

</body>
</html>

