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
<a href="${url2 }add"><span style="font-size: 20px;">添加试卷</span></a>&nbsp;&nbsp;
卷名：<input type="text" name="juanming" value="${juanming}">

<input type="submit" value="搜索">
</div>
</form>

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">卷名</td>
      <td class="td_bg"  height="23">课程</td>
      <td class="td_bg"  height="23">总分</td>
      <td class="td_bg"  height="23">考试时间</td>
      <td class="td_bg"  height="23">难度</td>
      <td class="td_bg"  height="23">状态</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.juanming }</td>
      <td class="td_bg"  height="23">${bean.kecheng.mingchen }</td>
      <td class="td_bg"  height="23">${bean.zongfen }</td>
      <td class="td_bg"  height="23">${bean.examtime }</td>
      <td class="td_bg"  height="23">${bean.nandu }</td>
      <td class="td_bg"  height="23">${bean.zhuangtai }</td>

      
      <td class="td_bg" >


      <a href="${url2 }update?id=${bean.id }"><span style="font-size: 15px;">修改</span></a>&nbsp;
      <a href="${url2 }delete?id=${bean.id }" onclick="return confirm('确定要删除吗?');"><span style="font-size: 15px;">删除</span></a>&nbsp;
      
      <a href="${url2 }update5?id=${bean.id }"><span style="font-size: 15px;">自动组卷</span></a>&nbsp;
  	
  	<a href="shijuanitemmethod!shijuanitemlist2?shijuanid=${bean.id }" ><span style="font-size: 15px;">手动调整</span></a>&nbsp;
  	
  	<a href="shijuanitemmethod!shijuanitemlist?id=${bean.id }"  target="_blank"><span style="font-size: 15px;">查看试卷详情</span></a>&nbsp;
  	
  	<c:if test="${bean.zhuangtai=='未使用'}">
  	<a href="${url2 }delete2?id=${bean.id }" onclick="return confirm('确定要使用试卷吗?'); "><span style="font-size: 15px;">使用试卷</span></a>&nbsp;
  	</c:if>
  	
  	<c:if test="${bean.zhuangtai=='使用中'}">
  	<a href="${url2 }delete3?id=${bean.id }" onclick="return confirm('确定要停用吗?'); "><span style="font-size: 15px;">停用</span></a>&nbsp;
  	</c:if>
      
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

