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


</div>
</form>

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">试卷名</td>
      <td class="td_bg"  height="23">给分状态</td>
      <td class="td_bg"  height="23">题目</td>
      <td class="td_bg"  height="23">题目类型</td>
      
      <td class="td_bg"  height="23">正确答案</td>
      <td class="td_bg"  height="23">考生答案</td>
      <td class="td_bg"  height="23">试卷分值</td>
      <td class="td_bg"  height="23">得分</td>
      <td class="td_bg"  height="23">点评</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23"> ${bean.shijuanitem.shijuan.juanming}&nbsp;</td>
      <td class="td_bg"  height="23"> ${bean.shifougeifen}&nbsp;</td>
      <td class="td_bg"  height="23">${bean.shijuanitem.shiti.wenti}&nbsp;</td>
      <td class="td_bg"  height="23"> ${bean.shijuanitem.shiti.leixing}&nbsp;</td>
      
      <td class="td_bg"  height="23">${bean.shijuanitem.shiti.daan}&nbsp;</td>
      <td class="td_bg"  height="23"> ${bean.wodedaan}&nbsp;</td>
      
      <td class="td_bg"  height="23"> ${bean.shijuanitem.fenzhi}&nbsp;</td>
      <td class="td_bg"  height="23">  ${bean.defen}&nbsp;</td>
      <td class="td_bg"  height="23"> ${bean.dianping}&nbsp;</td>

      
      <td class="td_bg" >


      <c:if test="${bean.shifougeifen=='未给分'}">
    <a  href="mingximethod!mingxiupdate?id=${bean.id}&examid=${examid }"><span>评分</span></a>
</c:if>
     
      
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

