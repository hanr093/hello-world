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
var displayBar =true;
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
<body  > 
<form name="exam" method="post"  action="method!kaoshiadd3">

 
<div align="center">
<span style="color: red;">
  <input type="button" value="返回"  onclick="javascript:history.go(-1)" />
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</span>

<br/>

<br/>

选择题：${zhengque1 }
<table border="1" width="80%">
  <tr>
  
    <th width="150">问题</th>
    <th width="150">A选项</th>
    <th width="150">B选项</th>
    <th width="150">C选项</th>
    <th width="150">D选项</th>
    <th width="150">我的答案</th>
    <th width="150">正确答案</th>
    
  </tr>
  <c:forEach items="${list1}" var="bean" varStatus="v">
  <tr>

    <td>${bean.shijuanitem.shiti.wenti }</td>
    <td>${bean.shijuanitem.shiti.a }</td>
    <td>${bean.shijuanitem.shiti.b }</td>
    <td>${bean.shijuanitem.shiti.c }</td>
    <td>${bean.shijuanitem.shiti.d }</td>

    <td>
   ${bean.wodedaan}
    </td>
     <td>
   ${bean.shijuanitem.shiti.daan}
    </td>
     <td>
  
    </td>
  </tr>
  </c:forEach>
  
</table>

<br/>
<br/>
<br/>
判断题：${zhengque2 }
<table border="1" width="80%">
  <tr>
 
    <th width="150">问题</th>


    <th width="150">我的答案</th>
     <th width="150">正确答案</th>
    
  </tr>
  <c:forEach items="${list2}" var="bean" varStatus="v">
  <tr>

    <td>${bean.shijuanitem.shiti.wenti }</td>

	 <td>
   ${bean.wodedaan}
    </td>
     <td>
   ${bean.shijuanitem.shiti.daan}
    </td>
   

    
  </tr>
  </c:forEach>
  
</table>
<br/>
<br/>
<br/>
填空题：
<table border="1" width="80%">
  <tr>

    <th width="150">问题</th>


    <th width="150">我的答案</th>
    

     <th width="150">正确答案</th>
    
  </tr>
  <c:forEach items="${list3}" var="bean" varStatus="v">
  <tr>

    <td>${bean.shijuanitem.shiti.wenti }</td>



   <td>
   <textarea rows="7" cols="50" readonly="readonly">${bean.wodedaan}</textarea>
   
    </td>
     <td>
      <textarea rows="7" cols="50" readonly="readonly">${bean.shijuanitem.shiti.daan}</textarea>
   
    </td>
    
  </tr>
  </c:forEach>
  
</table>



<br/>
<br/>
<br/>
问答题：
<table border="1" width="80%">
  <tr>

    <th width="150">问题</th>


    <th width="150">我的答案</th>
     <th width="150">正确答案</th>
   
  </tr>
  <c:forEach items="${list4}" var="bean" varStatus="v">
  <tr>

    <td>${bean.shijuanitem.shiti.wenti }</td>



   <td>
   <textarea rows="7" cols="50" readonly="readonly">${bean.wodedaan}</textarea>
   
    </td>
     <td>
      <textarea rows="7" cols="50" readonly="readonly">${bean.shijuanitem.shiti.daan}</textarea>
   
    </td>
   
  </tr>
  </c:forEach>
  
</table>



</div>

 
</form>

</body>
</html>

