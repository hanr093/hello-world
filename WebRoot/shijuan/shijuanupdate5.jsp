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


 
<script language="javascript" type="text/javascript">

function checkform()
{
	 
	

	
	
	var reg1 =  /^\d+$/;
	 
	

	if (document.getElementById('xzt1id').value.match(reg1) == null)
	{
		alert("简单选择题数量必须为正整数");
		return false;
	}
	if (document.getElementById('xzt2id').value.match(reg1) == null)
	{
		alert("中等选择题数量必须为正整数");
		return false;
	}
	if (document.getElementById('xzt3id').value.match(reg1) == null)
	{
		alert("复杂选择题数量必须为正整数");
		return false;
	}
	if (document.getElementById('xzt4id').value.match(reg1) == null)
	{
		alert("每道选择题分值必须为正整数");
		return false;
	}
	

 	
 	if (document.getElementById('pdt1id').value.match(reg1) == null)
	{
		alert("简单判断题数量必须为正整数");
		return false;
	}
	if (document.getElementById('pdt2id').value.match(reg1) == null)
	{
		alert("中等判断题数量必须为正整数");
		return false;
	}
	if (document.getElementById('pdt3id').value.match(reg1) == null)
	{
		alert("复杂判断题数量必须为正整数");
		return false;
	}
	if (document.getElementById('pdt4id').value.match(reg1) == null)
	{
		alert("每道判断题分值必须为正整数");
		return false;
	}
 	
 	
 	if (document.getElementById('tkt1id').value.match(reg1) == null)
	{
		alert("简单填空题数量必须为正整数");
		return false;
	}
	if (document.getElementById('tkt2id').value.match(reg1) == null)
	{
		alert("中等填空题数量必须为正整数");
		return false;
	}
	if (document.getElementById('tkt3id').value.match(reg1) == null)
	{
		alert("复杂填空题数量必须为正整数");
		return false;
	}
	if (document.getElementById('tkt4id').value.match(reg1) == null)
	{
		alert("每道填空题分值必须为正整数");
		return false;
	}
	
	
	if (document.getElementById('wdt1id').value.match(reg1) == null)
	{
		alert("简单问答题数量必须为正整数");
		return false;
	}
	if (document.getElementById('wdt2id').value.match(reg1) == null)
	{
		alert("中等问答题数量必须为正整数");
		return false;
	}
	if (document.getElementById('wdt3id').value.match(reg1) == null)
	{
		alert("复杂问答题数量必须为正整数");
		return false;
	}
	if (document.getElementById('wdt4id').value.match(reg1) == null)
	{
		alert("每道问答题分值必须为正整数");
		return false;
	}
 	
	
	return true;
	
}


</script>
 

<body>
<input onClick="switchBar(this)" type="button" value="关闭左边管理菜单" name="SubmitBtn" />
<br/>

<form method="post"   action="${url }" onsubmit="return checkform()" >
<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">卷名:${bean.juanming }</td>
      <td class="td_bg"  height="23">课程:${bean.kecheng.mingchen }</td>
      <td class="td_bg"  height="23">考试时间:${bean.examtime }</td>
      <td class="td_bg"  height="23">难度:${bean.nandu }</td>


    </tr>
   
   
    
    
  
    <tr>
      <td class="td_bg"  height="23">简单选择题数量:<input type="text" name="xzt1"  id='xzt1id'   /></td>
      <td class="td_bg"  height="23">中等选择题数量:<input type="text" name="xzt2"  id='xzt2id'   /></td>
      <td class="td_bg"  height="23">复杂选择题数量:<input type="text" name="xzt3"  id='xzt3id'   /></td>
      <td class="td_bg"  height="23">每道选择题分值:<input type="text" name="xzt4"  id='xzt4id'   /></td>
    </tr>
    
   <tr>
      <td class="td_bg"  height="23">简单判断题数量:<input type="text" name="pdt1"  id='pdt1id'   /></td>
      <td class="td_bg"  height="23">中等判断题数量:<input type="text" name="pdt2"  id='pdt2id'   /></td>
      <td class="td_bg"  height="23">复杂判断题数量:<input type="text" name="pdt3"  id='pdt3id'   /></td>
      <td class="td_bg"  height="23">每道判断题分值:<input type="text" name="pdt4"  id='pdt4id'   /></td>
    </tr>
    
    <tr>
      <td class="td_bg"  height="23">简单填空题数量:<input type="text" name="tkt1"  id='tkt1id'   /></td>
      <td class="td_bg"  height="23">中等填空题数量:<input type="text" name="tkt2"  id='tkt2id'   /></td>
      <td class="td_bg"  height="23">复杂填空题数量:<input type="text" name="tkt3"  id='tkt3id'   /></td>
      <td class="td_bg"  height="23">每道填空题分值:<input type="text" name="tkt4"  id='tkt4id'   /></td>
    </tr>
    
    <tr>
      <td class="td_bg"  height="23">简单问答题数量:<input type="text" name="wdt1"  id='wdt1id'   /></td>
      <td class="td_bg"  height="23">中等问答题数量:<input type="text" name="wdt2"  id='wdt2id'   /></td>
      <td class="td_bg"  height="23">复杂问答题数量:<input type="text" name="wdt3"  id='wdt3id'   /></td>
      <td class="td_bg"  height="23">每道问答题分值:<input type="text" name="wdt4"  id='wdt4id'   /></td>
    </tr>
    
    
  <tr>
      <td class="td_bg"  height="23"  colspan="4" align="center">
       <input type="submit" value="提交"/>
      &nbsp; &nbsp; &nbsp; &nbsp;
      
      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" />
      
      </td>
     
    </tr>
    
  </tbody>
</table>

</form>

</body>
</html>

