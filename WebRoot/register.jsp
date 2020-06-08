<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>新用户注册</title>
		<%
		response.addHeader("Cache-Control", "no-store, must-revalidate"); 
		response.addHeader("Expires", new java.util.Date().getTime()+"");
		%>
		
	<style type="text/css">
#regdiv {
	position: absolute;
	width: 700px;
	height: 500px;
	background-image: url(img/b2c_04.jpg);
}
</style>
<script language="javascript" type="text/javascript" src="js/jquery.min.js"></script>

<script language="javascript" type="text/javascript">

function checkregisterform()
{
	 
	 if (document.getElementById('usernameid').value=="")
	{
		alert("用户名不能为空");
		return false;
	}
	var valid=/^\w+$/;
	if(!valid.test(document.getElementById('usernameid').value)){
		alert("用户名必须是数字、字母或下划线");
		return false;
	}

	if (document.getElementById('passwordid').value=="")
	{
		alert("密码不能为空");
		return false;
	}
	if (document.getElementById('passwordid').value.length<6)
	{
		alert("密码长度必须大于6位");
		return false;
	}
	if (document.getElementById('password2id').value != document.getElementById('passwordid').value)
	{
		alert("确认密码与密码不一致");
		return false;
	}	 
	if (document.getElementById('xingmingid').value=="")
	{
		alert("姓名不能为空");
		return false;
	}
	
	if (document.getElementById('telid').value=="")
	{
		alert("手机号码不能为空");
		return false;
	}
	
	//验证手机格式
	valid=/^0?1[3,4,5,6,7,8,9][0,1,2,3,4,5,6,7,8,9]\d{8}$/;  
	if(!valid.test(document.getElementById('telid').value)){
		alert("请输入正确的手机号，如13000000000");
		return false;
	}
	
	return true;

}





</script>


</head>


	<body>



		<div align="center">

			<br />
			<br />
			
			
			<form action="method!register" method="post"  onsubmit="return checkregisterform()">
				<table align="center" border="1" cellpadding="5" cellspacing="3" width="80%">
				
					<tr>
						<td colspan="2">
							学生注册
						</td>
						
					</tr>
				
					<tr>
						<td>
							用户名：
						</td>
						<td>
							<input type="text" name="username" style="width: 250px;"  id="usernameid"/>
						</td>
					</tr>
					<tr>
						<td>
							密码：
						</td>
						<td>
							<input type="password" name="password" style="width: 250px;" id="passwordid"/>
						</td>
					</tr>
					<tr>
						<td>
							确认密码：
						</td>
						<td>
							<input type="password" name="password2" style="width: 250px;" id="password2id"/>
						</td>
					</tr>
					<tr>
						<td>
							姓名：
						</td>
						<td>
							<input type="text" name="xingming" style="width: 250px;" id="xingmingid"/>
						</td>
					</tr>
					<tr>
						<td>
							手机号码：
						</td>
						<td>
							<input type="text" name="tel" style="width: 250px;" id="telid"/>
						</td>
					</tr>
					
					<tr>
						<td>
							学院：
						</td>
						<td>
							<input type="text" name="xueyuan" style="width: 250px;" id="xueyuanid"/>
						</td>
					</tr>
					
					<tr>
						<td>
							专业：
						</td>
						<td>
							<input type="text" name="zhuanye" style="width: 250px;" id="zhuanyeid"/>
						</td>
					</tr>
					
					
					
					<tr>

						<td colspan="2" >
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="注册"  />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="返回"  onclick="window.location.href='login.jsp';"/>
						</td>
					</tr>
				</table>
</form>

		</div>

	</body>

</html>
