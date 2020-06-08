package action;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import model.Kecheng;
import util.Pager;
import dao.KechengDao;


public class KechengAction extends BaseAction{
	
	
	private static final long serialVersionUID  = -4304509122548259589L;
	
	private KechengDao kechengDao;
	

	public KechengDao getKechengDao() {
		return kechengDao;
	}

	public void setKechengDao(KechengDao kechengDao) {
		this.kechengDao = kechengDao;
	}

	
	
	
	//课程列表
	public String kechenglist()  {
		HttpServletRequest request = this.getRequest();
		
		String mingchen = request.getParameter("mingchen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		if(mingchen!=null&&!"".equals(mingchen)){
			sb.append(" mingchen like '%"+mingchen+"%'");
			sb.append(" and ");


			request.setAttribute("mingchen", mingchen);
		}
	
		sb.append(" deletestatus=0   order by id desc");
		String where = sb.toString();

		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kechengDao.selectBeanCount(where.replace("order by id desc", ""));
		request.setAttribute("list", kechengDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "kechengmethod!kechenglist", "共有" + total + "条记录"));
		request.setAttribute("url", "kechengmethod!kechenglist");
		request.setAttribute("url2", "kechengmethod!kecheng");

		this.setUrl("kecheng/kechenglist.jsp");
		return SUCCESS;
	}

//跳转到添加课程页面
	public String kechengadd() {
		HttpServletRequest request = this.getRequest();
		
		
		request.setAttribute("url", "kechengmethod!kechengadd2");

		this.setUrl("kecheng/kechengadd.jsp");
		return SUCCESS;
	}
//添加课程操作
	public void kechengadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String mingchen = request.getParameter("mingchen");
		
		Kecheng bean = new Kecheng();
		
		bean.setMingchen(mingchen);
		
		kechengDao.insertBean(bean);
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='kechengmethod!kechenglist';</script>");

		
	}
//跳转到更新课程页面
	public String kechengupdate() {
		HttpServletRequest request = this.getRequest();
		

		Kecheng bean = kechengDao.selectBean(" where id= "+request.getParameter("id"));
		
		request.setAttribute("url", "kechengmethod!kechengupdate2?id="+bean.getId());
		
		request.setAttribute("bean", bean);
		this.setUrl("kecheng/kechengupdate.jsp");
		return SUCCESS;
	}
//更新课程操作
	public void kechengupdate2() throws IOException {
		
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String mingchen = request.getParameter("mingchen");
		
		
		Kecheng bean = kechengDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setMingchen(mingchen);
		
		kechengDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='kechengmethod!kechenglist';</script>");
		
	}

//查看课程操作
	public String kechengupdate3() {
		HttpServletRequest request = this.getRequest();

		Kecheng bean = kechengDao.selectBean(" where id= "+request.getParameter("id"));
		
		request.setAttribute("bean", bean);
		
		this.setUrl("kecheng/kechengupdate3.jsp");
		return SUCCESS;
	}
//删除课程操作
	public void kechengdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		Kecheng bean = kechengDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setDeletestatus(1);
		
		kechengDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='kechengmethod!kechenglist';</script>");
	}
	
	
	//课程列表
	public String kechenglist2()  {
		HttpServletRequest request = this.getRequest();
		
		String mingchen = request.getParameter("mingchen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		if(mingchen!=null&&!"".equals(mingchen)){
			sb.append(" mingchen like '%"+mingchen+"%'");
			sb.append(" and ");


			request.setAttribute("mingchen", mingchen);
		}
	
		sb.append(" deletestatus=0   order by id desc");
		String where = sb.toString();

		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kechengDao.selectBeanCount(where.replace("order by id desc", ""));
		request.setAttribute("list", kechengDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "kechengmethod!kechenglist2", "共有" + total + "条记录"));
		request.setAttribute("url", "kechengmethod!kechenglist2");
		request.setAttribute("url2", "kechengmethod!kecheng");

		this.setUrl("kecheng/kechenglist2.jsp");
		return SUCCESS;
	}
	
	
	
}
