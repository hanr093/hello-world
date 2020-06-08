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
<a href="${url2 }add?shijuanid=${shijuanid }"><span style="font-size: 20px;">添加新试题</span></a>&nbsp;&nbsp;


类型:<select name="leixing">
     <option value="">所有选项</option>
    <option  value="选择题" <c:if test="${leixing=='选择题' }">selected</c:if>>选择题</option>
    <option  value="判断题" <c:if test="${leixing=='判断题' }">selected</c:if>>判断题</option>
    <option  value="填空题" <c:if test="${leixing=='填空题' }">selected</c:if>>填空题</option>
    <option  value="问答题" <c:if test="${leixing=='问答题' }">selected</c:if>>问答题</option>
    
     </select>
     
     难度:<select name="nandu">
     <option value="">所有选项</option>
    <option  value="简单" <c:if test="${nandu=='简单' }">selected</c:if>>简单</option>
    <option  value="中等" <c:if test="${nandu=='中等' }">selected</c:if>>中等</option>
    <option  value="复杂" <c:if test="${nandu=='复杂' }">selected</c:if>>复杂</option>
    
     </select>
     
     
     问题：<input type="text"  name="wenti" value="${wenti }"/>

<input type="submit" value="搜索">
</div>
</form>

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">分值</td>
      <td class="td_bg"  height="23">难度</td>
      <td class="td_bg"  height="23">类型</td>
      <td class="td_bg"  height="23">问题</td>
     

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.fenzhi }&nbsp;</td>
      <td class="td_bg"  height="23">${bean.shiti.nandu }&nbsp;</td>
      <td class="td_bg"  height="23">${bean.shiti.leixing }&nbsp;</td>
      <td class="td_bg"  height="23">${bean.shiti.wenti }&nbsp;</td>
      

      
      <td class="td_bg" >


     <c:if test="${bean.shiti.leixing=='选择题' }">
    <a href="shitimethod!shitiupdate3?id=${bean.shiti.id }">查看</a> &nbsp; &nbsp; &nbsp;
    </c:if>
    
    <c:if test="${bean.shiti.leixing=='判断题' }">
    <a href="shitimethod!shitiupdate30?id=${bean.shiti.id }">查看</a> &nbsp; &nbsp; &nbsp;
    </c:if>
    
    <c:if test="${bean.shiti.leixing=='填空题' }">
    <a href="shitimethod!shitiupdate300?id=${bean.shiti.id }">查看</a> &nbsp; &nbsp; &nbsp;
    </c:if>
    
    <c:if test="${bean.shiti.leixing=='问答题' }">
    <a href="shitimethod!shitiupdate3000?id=${bean.shiti.id }">查看</a> &nbsp; &nbsp; &nbsp;
    </c:if>
    
    
  	<a href="${url2 }update?id=${bean.id }&shijuanid=${shijuanid }">修改分值</a> &nbsp; &nbsp; &nbsp;
  	
  	<a href="${url2 }delete?id=${bean.id }&shijuanid=${shijuanid }" onclick="return confirm('确定要删除吗?'); ">删除</a>
      
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

