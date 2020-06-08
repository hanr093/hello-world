package action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import model.Ziliao;
import util.Pager;
import util.Util;
import dao.KechengDao;
import dao.ZiliaoDao;


public class ZiliaoAction extends BaseAction{
	
	
	private static final long serialVersionUID  = -4304509122548259589L;
	
	private KechengDao kechengDao;
	

	public KechengDao getKechengDao() {
		return kechengDao;
	}

	public void setKechengDao(KechengDao kechengDao) {
		this.kechengDao = kechengDao;
	}

	private ZiliaoDao ziliaoDao;
	
	
	public ZiliaoDao getZiliaoDao() {
		return ziliaoDao;
	}

	public void setZiliaoDao(ZiliaoDao ziliaoDao) {
		this.ziliaoDao = ziliaoDao;
	}
	
	

	//课程资料列表
	public String ziliaolist()  {
		HttpServletRequest request = this.getRequest();
		
		String mingchen = request.getParameter("mingchen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		if(mingchen!=null&&!"".equals(mingchen)){
			sb.append(" kecheng.mingchen like '%"+mingchen+"%'");
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
		int total = ziliaoDao.selectBeanCount(where.replace("order by id desc", ""));
		request.setAttribute("list", ziliaoDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "ziliaomethod!ziliaolist", "共有" + total + "条记录"));
		request.setAttribute("url", "ziliaomethod!ziliaolist");
		request.setAttribute("url2", "ziliaomethod!ziliao");

		this.setUrl("ziliao/ziliaolist.jsp");
		return SUCCESS;
	}

//跳转到添加课程资料页面
	public String ziliaoadd() {
		HttpServletRequest request = this.getRequest();
		
		request.setAttribute("kechenglist", kechengDao.selectBeanList(0, 9999, " where deletestatus=0 "));
		request.setAttribute("url", "ziliaomethod!ziliaoadd2");

		this.setUrl("ziliao/ziliaoadd.jsp");
		return SUCCESS;
	}
	
	
	private File uploadfile;;


	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	
//添加课程资料操作
	public void ziliaoadd2() throws Exception {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String shuoming = request.getParameter("shuoming");
		
		String kechengid = request.getParameter("kechengid");
		
		Ziliao bean = new Ziliao();
		
		bean.setCtime(Util.getRiqi());
		bean.setKecheng(kechengDao.selectBean(" where id= "+kechengid));

		bean.setShuoming(shuoming);
		
		if(uploadfile!=null){
			String filename = request.getParameter("filename");
			String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();
			String filename2 = filename.substring(filename.lastIndexOf("\\")+1,filename.length());
			String path = time +"_"+filename2;
			File file = new File(savaPath + path);
			Util.copyFile(uploadfile, file);
			String uploadpath = time +".zip";
			Util.createZip(path, uploadpath, savaPath);
			
			bean.setPath(uploadpath);
		}
		
		ziliaoDao.insertBean(bean);
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='ziliaomethod!ziliaolist';</script>");

		
	}
//跳转到更新课程资料页面
	public String ziliaoupdate() {
		HttpServletRequest request = this.getRequest();
		

		Ziliao bean = ziliaoDao.selectBean(" where id= "+request.getParameter("id"));
		
		request.setAttribute("url", "ziliaomethod!ziliaoupdate2?id="+bean.getId());
		
		request.setAttribute("bean", bean);
		this.setUrl("ziliao/ziliaoupdate.jsp");
		return SUCCESS;
	}
//更新课程资料操作
	public void ziliaoupdate2() throws IOException {
		
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String shuoming = request.getParameter("shuoming");
		
		
		Ziliao bean = ziliaoDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setShuoming(shuoming);
		
		ziliaoDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='ziliaomethod!ziliaolist';</script>");
		
	}

//查看课程资料操作
	public String ziliaoupdate3() {
		HttpServletRequest request = this.getRequest();

		Ziliao bean = ziliaoDao.selectBean(" where id= "+request.getParameter("id"));
		
		request.setAttribute("bean", bean);
		
		this.setUrl("ziliao/ziliaoupdate3.jsp");
		return SUCCESS;
	}
//删除课程资料操作
	public void ziliaodelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		Ziliao bean = ziliaoDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setDeletestatus(1);
		
		ziliaoDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='ziliaomethod!ziliaolist';</script>");
	}
	
	
	//课程资料列表
	public String ziliaolist2()  {
		HttpServletRequest request = this.getRequest();
		
		String mingchen = request.getParameter("mingchen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		if(mingchen!=null&&!"".equals(mingchen)){
			sb.append(" kecheng.mingchen like '%"+mingchen+"%'");
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
		int total = ziliaoDao.selectBeanCount(where.replace("order by id desc", ""));
		request.setAttribute("list", ziliaoDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "ziliaomethod!ziliaolist2", "共有" + total + "条记录"));
		request.setAttribute("url", "ziliaomethod!ziliaolist2");
		request.setAttribute("url2", "ziliaomethod!ziliao");

		this.setUrl("ziliao/ziliaolist2.jsp");
		return SUCCESS;
	}
	
	
	
}
