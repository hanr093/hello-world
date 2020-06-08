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
	 
	
//验证正整数
var reg1 =  /^\d+$/;
	 
	if (document.getElementById('defenid').value.match(reg1) == null)
	{
		alert("得分必须为正整数");
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
      <td class="td_bg"  height="23">问题:</td>
      <td class="td_bg" >
      <textarea rows="7" cols="80" name="wenti"   readonly="readonly">${bean.shijuanitem.shiti.wenti }</textarea>
      </td>
     
    </tr>
    
    
    <tr>
      <td class="td_bg"  height="23">正确答案:</td>
      <td class="td_bg" >
      <textarea rows="7" cols="80" name="daan"   readonly="readonly">${bean.shijuanitem.shiti.daan }</textarea>
      </td>
     
    </tr>
    
    
    <tr>
      <td class="td_bg"  height="23">分值:</td>
      <td class="td_bg" >
      <input type="text"name="examtime"  id='examtimeid'  value="${bean.shijuanitem.fenzhi }"  size="50"/>
      </td>
     
    </tr>
    
    
    
    <tr>
      <td class="td_bg"  height="23">我的答案:</td>
      <td class="td_bg" >
     <textarea rows="7" cols="80" name="daan"  readonly="readonly">${bean.wodedaan }</textarea>
      </td>
     
    </tr>
    
    
    <tr>
      <td class="td_bg"  height="23">得分:</td>
      <td class="td_bg" >
      <input type="text"name="defen"  id='defenid'   size="50"/>
      </td>
     
    </tr>
    
    
    
    <tr>
      <td class="td_bg"  height="23">点评:</td>
      <td class="td_bg" >
     <textarea rows="7" cols="80" name="dianping"  ></textarea>
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

