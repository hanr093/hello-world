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
       				
     				 
     				 <input type="text" name="wenti"  id='wentiid'  value="${bean.nandu }"  readonly="readonly" size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">问题:</td>
      <td class="td_bg" >
      <input type="text" name="wenti"  id='wentiid'  value="${bean.wenti }"  readonly="readonly" size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">A选项:</td>
      <td class="td_bg" >
      <input type="text" name="a"  id='aid' value="${bean.a }" readonly="readonly"  size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">B选项:</td>
      <td class="td_bg" >
      <input type="text" name="b"  id='bid'  value="${bean.b }"  readonly="readonly" size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">C选项:</td>
      <td class="td_bg" >
      <input type="text" name="c" id="cid"  value="${bean.c }"  readonly="readonly" size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">D选项:</td>
      <td class="td_bg" >
      <input type="text" name="d"  id='did' value="${bean.d }" readonly="readonly"  size="50"/>
      </td>
     
    </tr>
    
     <tr>
      <td class="td_bg"  height="23">答案:</td>
      <td class="td_bg" >
      
       <input type="text" name="d"  id='did' value="${bean.daan }" readonly="readonly"  size="50"/>
      				
      </td>
     
    </tr>
    
    
    
    <tr>
      <td class="td_bg"  height="23">操作</td>
      <td class="td_bg" >
    
      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" />
      </td>
     
    </tr>
    </from>
</tbody>
</table>

</body>
</html>

