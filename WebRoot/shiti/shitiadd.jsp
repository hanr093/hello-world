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
	 
	

	if (document.getElementById('wentiid').value=="")
	{
		alert("问题不能为空");
		return false;
	}
	if (document.getElementById('aid').value=="")
	{
		alert("A选项不能为空");
		return false;
	}
	
	if (document.getElementById('bid').value=="")
	{
		alert("B选项不能为空");
		return false;
	}
	if (document.getElementById('cid').value=="")
	{
		alert("C选项不能为空");
		return false;
	}
	if (document.getElementById('did').value=="")
	{
		alert("D选项不能为空");
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
   					 <option  value="简单" >简单</option>
   					 <option  value="中等" >中等</option>
    				 <option  value="复杂" >复杂</option>
     				 </select>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">问题:</td>
      <td class="td_bg" >
      <input type="text" name="wenti"  id='wentiid'   size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">A选项:</td>
      <td class="td_bg" >
      <input type="text" name="a"  id='aid'   size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">B选项:</td>
      <td class="td_bg" >
      <input type="text" name="b"  id='bid'   size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">C选项:</td>
      <td class="td_bg" >
      <input type="text" name="c" id="cid"   size="50"/>
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">D选项:</td>
      <td class="td_bg" >
      <input type="text" name="d"  id='did'   size="50"/>
      </td>
     
    </tr>
    
     <tr>
      <td class="td_bg"  height="23">答案:</td>
      <td class="td_bg" >
      				<select name="daan">
   					 <option  value="A" >A</option>
   					 <option  value="B" >B</option>
    				 <option  value="C" >C</option>
    				 <option  value="D" >D</option>
     				 </select>
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

