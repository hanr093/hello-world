<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html> 
<head>  
<meta Content-type='text/html' charset="utf-8"> 
<script> 

var   timeLeft   =   ${examtime }   *   60   *   1000; 
function   countTime() 
{ 		
		
		//alert(kaoshishijian);
		//timeLeft   =   60   *   60   *   1000; 
		//alert(timeLeft);
 
        var   startMinutes   =   parseInt(timeLeft   /   (60   *   1000),   10); 
        var   startSec   =   parseInt((timeLeft   -   startMinutes   *   60   *   1000)/1000) 
        document.getElementById('time').innerText   =   "剩余时间： "   +   startMinutes   +   "分钟 "   +   startSec   +   "秒 "; 
        timeLeft   =   timeLeft   -   1000; 
        var t = setTimeout( 'countTime() ',1000); 
 
if(timeLeft   <   0) 
{ 
        clearInterval(t);
 
        alert( "考试时间到！"); 
 
        document.exam.submit();
 
} 
 
} 
 
</script> 
</head>

<body   onload= "countTime() "> 
<form name="exam" method="post"  action="exammethod!exam2">
<input  name='xuanzetishuliang' type="hidden"   value="${shuliang1 }"/>
<input  name='panduantishuliang' type="hidden"   value="${shuliang2 }"/>
<input  name='tiankongtishuliang' type="hidden"   value="${shuliang3 }"/>
<input  name='wendantishuliang' type="hidden"   value="${shuliang4 }"/>
 
<input  name='shijuanid' type="hidden"   value="${shijuanid }"/> 

<div align="center">
<span style="color: red;">
考试时间：${examtime }分钟
<br/>
科目：${kecheng }
<br/>
<div id='time'></div>
</span>

<br/>

选择题：
<table border="1" width="80%">
  <tr>
  <th width="150">序号</th>
    <th width="150">问题</th>
    <th width="150">A选项</th>
    <th width="150">B选项</th>
    <th width="150">C选项</th>
    <th width="150">D选项</th>
    <th width="150">我的答案</th>
  </tr>
  <c:forEach items="${list1}" var="bean" varStatus="v">
  <tr>
   <td>第${v.index+1 }题</td>
    <td>${bean.shiti.wenti }</td>
    <td>${bean.shiti.a }</td>
    <td>${bean.shiti.b }</td>
    <td>${bean.shiti.c }</td>
    <td>${bean.shiti.d }</td>

    <td>
   <input  name='xuanzetiid${v.index }' type="hidden"  value="${bean.id }" />
    <select name="xuanzetidaan${v.index }"> 
    <option value="A">A</option>
    <option value="B">B</option>
    <option value="C">C</option>
    <option value="D">D</option>
    </select>
    </td>
  </tr>
  </c:forEach>
  
</table>

<br/>
<br/>
<br/>
判断题：
<table border="1" width="80%">
  <tr>
  <th width="150">序号</th>
    <th width="150">问题</th>


    <th width="150">我的答案</th>
  </tr>
  <c:forEach items="${list2}" var="bean" varStatus="v">
  <tr>
   <td>第${v.index+1 }题</td>
    <td>${bean.shiti.wenti }</td>



    <td>
   <input  name='panduantiid${v.index }' type="hidden"   value="${bean.id }" />
    <select name="panduantidaan${v.index }"> 
    <option value="正确">正确</option>
    <option value="错误">错误</option>

    </select>
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
  <th width="150">序号</th>
    <th width="150">问题</th>


    <th width="150">我的答案</th>
  </tr>
  <c:forEach items="${list3}" var="bean" varStatus="v">
  <tr>
   <td>第${v.index+1 }题</td>
    <td>${bean.shiti.wenti }</td>



    <td>
   <input  name='tiankongtiid${v.index }' type="hidden"  value="${bean.id }" />
   <input name="tiankongtidaan${v.index }" type="text"  size="50">
   
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
  <th width="150">序号</th>
    <th width="150">问题</th>


    <th width="150">我的答案</th>
  </tr>
  <c:forEach items="${list4}" var="bean" varStatus="v">
  <tr>
   <td>第${v.index+1 }题</td>
    <td>${bean.shiti.wenti }</td>



    <td>
   <input  name='wendatiid${v.index }' type="hidden"  value="${bean.id }" />
   
   <textarea rows="7" cols="70" name="wendatidaan${v.index }"></textarea>
   
    </td>
  </tr>
  </c:forEach>
  
</table>


<table border="1">
  <tr>
    <th width="150" colspan="2">
   <input  type="submit"  value="提交试卷"> 
    
    </th>

  </tr>
 
  
</table>
</div>

 
</form>
 
</BODY> 
</html>



