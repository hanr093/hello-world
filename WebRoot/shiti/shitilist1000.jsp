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
<a href="${url2 }add1000?kechengid=${kechengid }"><span style="font-size: 20px;">添加新问答题</span></a>&nbsp;&nbsp;

问题：<input type="text"  name="wenti" value="${wenti }"/>
难度:<select name="nandu">
     <option value="">所有选项</option>
    <option  value="简单" <c:if test="${nandu=='简单' }">selected</c:if>>简单</option>
    <option  value="中等" <c:if test="${nandu=='中等' }">selected</c:if>>中等</option>
    <option  value="复杂" <c:if test="${nandu=='复杂' }">selected</c:if>>复杂</option>
    
     </select>
     
<input type="submit" value="搜索">
</div>
</form>

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">课程</td>
      <td class="td_bg"  height="23">难度</td>
      <td class="td_bg"  height="23">问题</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.kecheng.mingchen }</td>
      <td class="td_bg"  height="23">${bean.nandu  }</td>
      <td class="td_bg"  height="23">${bean.wenti }</td>

      
      <td class="td_bg" >

      <a href="${url2 }update3000?id=${bean.id }"><span style="font-size: 15px;">查看</span></a>
      <a href="${url2 }update1000?id=${bean.id }"><span style="font-size: 15px;">修改</span></a>
      <a href="${url2 }delete1000?id=${bean.id }" onclick="return confirm('确定要删除吗?');"><span style="font-size: 15px;">删除</span></a>
      
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

