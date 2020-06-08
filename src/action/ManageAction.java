package action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;
import util.Pager;
import util.Util;
import dao.KechengDao;
import dao.UserDao;


public class ManageAction extends BaseAction{
	
	
	private static final long serialVersionUID = -4304509122548259589L;
	
	private UserDao userDao;
	
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao  = userDao;
	}

	
	private KechengDao kechengDao;
	

	
	public KechengDao getKechengDao() {
		return kechengDao;
	}

	public void setKechengDao(KechengDao kechengDao) {
		this.kechengDao = kechengDao;
	}
	
	

	//用户登录操作
	public String login() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userDao.selectBean(" where username = '"+username +"' and password= '"+password +"' and deletestatus=0 ");
		if (user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("index.jsp");
			return "redirect";
		} else {
			writer.print("<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
	
	
	
	//用户退出操作
	public String loginout() {
		HttpServletRequest request = this.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
	
	
	//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/password.jsp");
		return SUCCESS;
	}
	
	//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"'");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			
			writer.print("<script language=javascript>alert('修改成功');</script>");
			
		}else{
			writer.print("<script language=javascript>alert('用户名或者密码错误');</script>");
		}
	}
	
	
	//教师列表
	public String userlist()  {
		HttpServletRequest request = this.getRequest();
		
		String username = request.getParameter("username");
		
		String xingming = request.getParameter("xingming");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		if(username!=null&&!"".equals(username)){
			sb.append(" username like '%"+username+"%'");
			sb.append(" and ");


			request.setAttribute("username", username);
		}
		
		if(xingming!=null&&!"".equals(xingming)){
			sb.append(" xingming like '%"+xingming+"%'");
			sb.append(" and ");


			request.setAttribute("xingming", xingming);
		}
	
		sb.append(" deletestatus=0  and role=1  order by id desc");
		String where = sb.toString();

		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replace("order by id desc", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist");
		request.setAttribute("url2", "method!user");

		this.setUrl("user/userlist.jsp");
		return SUCCESS;
	}

	
//跳转到添加教师页面
	public String useradd() {
		HttpServletRequest request = this.getRequest();
		
		request.setAttribute("kechenglist", kechengDao.selectBeanList(0, 9999, " where deletestatus=0 "));
		request.setAttribute("url", "method!useradd2");

		this.setUrl("user/useradd.jsp");
		return SUCCESS;
	}
//添加教师操作
	public void useradd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String tel = request.getParameter("tel");
		String kechengid = request.getParameter("kechengid");
		String username = request.getParameter("username");
		String xingming = request.getParameter("xingming");
		
		User bean = userDao.selectBean(" where deletestatus=0 and username='"+username+"' ");
		
		if(bean!=null){
			
			writer.print("<script language=javascript>alert('操作失败，该用户名已经存在');window.location.href='method!userlist';</script>");
			return ;
			
		}
		
		bean = new User();
		
		bean.setCtime(Util.getRiqi());
		bean.setKecheng(kechengDao.selectBean(" where id= "+kechengid));
		bean.setPassword("111111");
		bean.setRole(1);
		bean.setTel(tel);
		bean.setUsername(username);
		bean.setXingming(xingming);
		
		userDao.insertBean(bean);
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");

		
	}
	
	
//跳转到更新教师页面
	public String userupdate() {
		HttpServletRequest request = this.getRequest();
		
		request.setAttribute("kechenglist", kechengDao.selectBeanList(0, 9999, " where deletestatus=0 "));
		
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		
		request.setAttribute("url", "method!userupdate2?id="+bean.getId());
		
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate.jsp");
		return SUCCESS;
	}
//更新教师操作
	public void userupdate2() throws IOException {
		
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String tel = request.getParameter("tel");
		String kechengid = request.getParameter("kechengid");
		String xingming = request.getParameter("xingming");
		
		
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setKecheng(kechengDao.selectBean(" where id= "+kechengid));
		bean.setTel(tel);
		bean.setXingming(xingming);
		
		userDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
		
	}

//查看教师操作
	public String userupdate3() {
		HttpServletRequest request = this.getRequest();

		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		
		request.setAttribute("bean", bean);
		
		this.setUrl("user/userupdate3.jsp");
		return SUCCESS;
	}
//删除教师操作
	public void userdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setDeletestatus(1);
		
		userDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}
	
	
	
	//学生注册操作
	public void register() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String tel = request.getParameter("tel");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String xingming = request.getParameter("xingming");
		String xueyuan = request.getParameter("xueyuan");
		String zhuanye = request.getParameter("zhuanye");
		
		
		
		User bean = userDao.selectBean(" where username='"+username+"' and deletestatus=0 ");
		if(bean!=null){

			writer.print("<script language=javascript>alert('注册失败，该用户名已经存在');window.location.href='register.jsp';</script>");
			return ;
		}
		
		bean = new User();
		
		bean.setCtime(Util.getTime());
		bean.setPassword(password);
		bean.setRole(2);
		bean.setTel(tel);
		bean.setUsername(username);
		bean.setXingming(xingming);
		bean.setXueyuan(xueyuan);
		bean.setZhuanye(zhuanye);
		
		userDao.insertBean(bean);
		
		
		writer.print("<script language=javascript>alert('注册成功');window.location.href='login.jsp';</script>");


	}
	
	//学生用户管理
	public String userlist2()  {
		HttpServletRequest request = this.getRequest();
		
		String username = request.getParameter("username");
		
		String xingming = request.getParameter("xingming");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		if(username!=null&&!"".equals(username)){
			sb.append(" username like '%"+username+"%'");
			sb.append(" and ");


			request.setAttribute("username", username);
		}
		
		if(xingming!=null&&!"".equals(xingming)){
			sb.append(" xingming like '%"+xingming+"%'");
			sb.append(" and ");


			request.setAttribute("xingming", xingming);
		}
	
		sb.append(" deletestatus=0 and role=2  order by id desc");
		String where = sb.toString();

		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replace("order by id desc", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist2");
		request.setAttribute("url2", "method!user");

		this.setUrl("user/userlist2.jsp");
		return SUCCESS;
	}



//删除操作
	public void userdelete2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setDeletestatus(1);
		
		userDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist2';</script>");
	}
	
	
	
}
