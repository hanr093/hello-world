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
	 
	

	if (document.getElementById('juanmingid').value=="")
	{
		alert("卷名不能为空");
		return false;
	}
	if (document.getElementById('examtimeid').value=="")
	{
		alert("考试时间不能为空");
		return false;
	}
	
	var reg1 =  /^\d+$/;
	 
	

	if (document.getElementById('examtimeid').value.match(reg1) == null)
	{
		alert("考试时间必须为正整数");
		return false;
	}
	

 	
	
	return true;
	
}


</script>
 

<body>
<input onClick="switchBar(this)" type="button" value="关闭左边管理菜单" name="SubmitBtn" />

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    <form method="post"   action="${url }" onsubmit="return checkform()" >
    
    <tr>
      <td class="td_bg"  height="23">难度:</td>
      <td class="td_bg" >
      				 <select name="nandu">
   					 <option  value="简单" <c:if test="${bean.nandu=='简单' }">selected</c:if> >简单</option>
   					 <option  value="中等" <c:if test="${bean.nandu=='中等' }">selected</c:if> >中等</option>
    				 <option  value="复杂" <c:if test="${bean.nandu=='复杂' }">selected</c:if> >复杂</option>
     				 </select>
      </td>
     
    </tr>
    
    <tr>
      <td class="td_bg"  height="23">卷名:</td>
      <td class="td_bg" >
      <input type="text" name="juanming"  id='juanmingid' value="${bean.juanming }"   size="50"/>
      </td>
     
    </tr>
    
    <tr>
      <td class="td_bg"  height="23">考试时间(分钟):</td>
      <td class="td_bg" >
      <input type="text"name="examtime"  id='examtimeid' value="${bean.examtime }"  size="50"/>
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

