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

用户名：<input type="text" name="username" value="${username}">
姓名：<input type="text" name="xingming" value="${xingming}">

<input type="submit" value="搜索">
</div>
</form>

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
    
      <td class="td_bg"  height="23">用户名</td>
      <td class="td_bg"  height="23">密码</td>
      <td class="td_bg"  height="23">添加时间</td>
      <td class="td_bg"  height="23">姓名</td>
      <td class="td_bg"  height="23">手机号码</td>
      <td class="td_bg"  height="23">学院</td>
      <td class="td_bg"  height="23">专业</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.username }</td>
      <td class="td_bg"  height="23">${bean.password }</td>
      <td class="td_bg"  height="23">${bean.ctime }</td>
      <td class="td_bg"  height="23">${bean.xingming }</td>
      <td class="td_bg"  height="23">${bean.tel }</td>
  
	  <td class="td_bg"  height="23">${bean.xueyuan }</td>
	  <td class="td_bg"  height="23">${bean.zhuanye }</td>
      
      <td class="td_bg" >


      <a href="${url2 }delete2?id=${bean.id }" onclick="return confirm('确定要删除吗?');"><span style="font-size: 15px;">删除</span></a>
      
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

