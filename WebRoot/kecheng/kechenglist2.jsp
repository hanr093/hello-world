<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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


 

 

<body>
<input onClick="switchBar(this)" type="button" value="关闭左边管理菜单" name="SubmitBtn" />
<br/>
<form action="${url }" method="post">
<div align="left">
<br/>

课程名称：<input type="text" name="mingchen" value="${mingchen}">

<input type="submit" value="搜索">
</div>
</form>

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">课程名称</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.mingchen }</td>

      
      <td class="td_bg" >

      <a href="${url2 }update3?id=${bean.id }"><span style="font-size: 15px;">查看</span></a>&nbsp; &nbsp; &nbsp;
      
    <a href="shitimethod!shitilist?kechengid=${bean.id }"><span style="font-size: 15px;">选择题管理</span></a> &nbsp; &nbsp; &nbsp;
  	
  	<a href="shitimethod!shitilist10?kechengid=${bean.id }"><span style="font-size: 15px;">判断题管理</span></a> &nbsp; &nbsp; &nbsp;
  	
  	<a href="shitimethod!shitilist100?kechengid=${bean.id }"><span style="font-size: 15px;">填空题管理</span></a> &nbsp; &nbsp; &nbsp;
  	
  	<a href="shitimethod!shitilist1000?kechengid=${bean.id }"><span style="font-size: 15px;">问答题管理</span></a> &nbsp; &nbsp; &nbsp;
      
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

