<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考试系统</title>
<link href="Images/css1/left_css.css" rel="stylesheet" type="text/css">
</head>
<SCRIPT language=JavaScript>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</SCRIPT>
<body bgcolor="16ACFF">
<table width="98%" border="0" cellpadding="0" cellspacing="0" background="Images/tablemde.jpg">
  <tr>
    <td height="5" background="Images/tableline_top.jpg" bgcolor="#16ACFF"></td>
  </tr>
  
  
  
  
  <c:if test="${user.role==0}">
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(1); height=25>课程</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu1 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="kechengmethod!kechenglist" target=main>课程管理</A>
                  </TD>
                </TR>
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  
  
<tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2); height=25>用户</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu2 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="method!userlist" target=main>教师管理</A>
                  </TD>
                </TR>
                
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="method!userlist2" target=main>学生管理</A>
                  </TD>
                </TR>
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(3); height=25>试题库</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu3 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="kechengmethod!kechenglist2" target=main>试题库管理</A>
                  </TD>
                </TR>
                
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="shitimethod!shitilist2" target=main>试题库查询</A>
                  </TD>
                </TR>
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(4); height=25>试卷</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu4 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="shijuanmethod!shijuanlist" target=main>试卷管理</A>
                  </TD>
                </TR>
                
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(5); height=25>课程资料</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu5 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="ziliaomethod!ziliaolist" target=main>课程资料管理</A>
                  </TD>
                </TR>
                
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(6); height=25>考试记录</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu6 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="exammethod!examlist4" target=main>考试记录查询</A>
                  </TD>
                </TR>
                
                
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  </c:if>
  
  
  <c:if test="${user.role==2}">
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(1); height=25>考试</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu1 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="shijuanmethod!shijuanlist2" target=main>在线考试</A>
                  </TD>
                </TR>
                
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="exammethod!examlist" target=main>考试记录</A>
                  </TD>
                </TR>
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  
  
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2); height=25>课程资料</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu2 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="ziliaomethod!ziliaolist2" target=main>课程资料查询</A>
                  </TD>
                </TR>
                
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  </c:if>
  
  
  
  <c:if test="${user.role==1}">
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(1); height=25>阅卷</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu1 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="exammethod!examlist2" target=main>待阅试卷</A>
                  </TD>
                </TR>
                
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="exammethod!examlist3" target=main>我的阅卷</A>
                  </TD>
                </TR>
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  <tr>
    <td><TABLE width="97%" 
border=0 align=right cellPadding=0 cellSpacing=0 class=leftframetable>
      <TBODY>
        <TR>
          <TD height="25" style="background:url(Images/left_tt.gif) no-repeat">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <TD width="20"></TD>
          <TD class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2); height=25>课程资料</TD>
              </tr>
            </table>            </TD>
          </TR>
        <TR>
          <TD><TABLE id=submenu2 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="2%"><IMG src="Images/closed.gif"></TD>
                  <TD height=23>
                  <A href="ziliaomethod!ziliaolist2" target=main>课程资料查询</A>
                  </TD>
                </TR>
                
               
                
              </TBODY>
          </TABLE></TD>
        </TR>
      </TBODY>
    </TABLE></td>
  </tr>
  
  
  </c:if>
  
  
  <tr>
    <td height="5" background="Images/tableline_bottom.jpg"></td>
  </tr>
</table>
</body></html>

