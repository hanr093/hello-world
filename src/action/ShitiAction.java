package action;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Kecheng;
import model.Shiti;
import org.apache.struts2.ServletActionContext;

import util.Pager;
import dao.KechengDao;
import dao.ShitiDao;


public class ShitiAction extends BaseAction{
	
	
	private static final long serialVersionUID = -4304509122548259589L;
	
	private KechengDao kechengDao;
	

	public KechengDao getKechengDao() {
		return kechengDao;
	}

	public void setKechengDao(KechengDao kechengDao) {
		this.kechengDao  = kechengDao;
	}

	
	private ShitiDao shitiDao;
	
	
	public ShitiDao getShitiDao() {
		return shitiDao;
	}

	public void setShitiDao(ShitiDao shitiDao) {
		this.shitiDao = shitiDao;
	}

	
	
	
	//选择题列表
	public String shitilist() {
		HttpServletRequest request = this.getRequest();
		
		
		String kechengid = request.getParameter("kechengid");
		
		request.setAttribute("kechengid", kechengid);
		
		
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		

		if (nandu != null && !"".equals(nandu)) {
			sb.append("nandu like '%" + nandu + "%'");
			sb.append(" and ");
			request.setAttribute("nandu", nandu);
		}
		
		if (wenti != null && !"".equals(wenti)) {
			sb.append("wenti like '%" + wenti + "%'");
			sb.append(" and ");
			request.setAttribute("wenti", wenti);
		}
		
		
		
		sb.append("  leixing='选择题' and deletestatus=0  and kecheng.id="+kechengid+"  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = shitiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", shitiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "shitimethod!shitilist?kechengid="+kechengid, "共有" + total + "条记录"));
		request.setAttribute("url", "shitimethod!shitilist?kechengid="+kechengid);
		request.setAttribute("url2", "shitimethod!shiti");
		request.setAttribute("biaoti", "选择题管理");
		this.setUrl("shiti/shitilist.jsp");
		return SUCCESS;

	}
	
	
//跳转到添加选择题页面
	public String shitiadd() {
		HttpServletRequest request = this.getRequest();
		String kechengid = request.getParameter("kechengid");
		
		Kecheng kecheng = kechengDao.selectBean(" where id= "+kechengid);
		request.setAttribute("kecheng", kecheng);

		request.setAttribute("url", "shitimethod!shitiadd2?kechengid="+kechengid);
		request.setAttribute("biaoti", "选择题添加");
		this.setUrl("shiti/shitiadd.jsp");
		return SUCCESS;
	}
	
	
//添加选择题操作
	public void shitiadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String c = request.getParameter("c");
		String d = request.getParameter("d");
		String daan = request.getParameter("daan");
		String kechengid = request.getParameter("kechengid");
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");

		Shiti bean = new Shiti();
		bean.setA(a);
		bean.setB(b);
		bean.setC(c);
		bean.setD(d);
		bean.setDaan(daan);
		bean.setKecheng(kechengDao.selectBean(" where id= "+kechengid));
		bean.setLeixing("选择题");
		bean.setNandu(nandu);
		bean.setWenti(wenti);

		
		shitiDao.insertBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist?kechengid="+bean.getKecheng().getId()+"';</script>");
		
	}
//跳转到更新选择题页面
	public String shitiupdate() {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "shitimethod!shitiupdate2?id="+bean.getId());
		request.setAttribute("biaoti", "选择题修改");
		this.setUrl("shiti/shitiupdate.jsp");
		return SUCCESS;
	}
//更新选择题操作
	public void shitiupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String c = request.getParameter("c");
		String d = request.getParameter("d");
		String daan = request.getParameter("daan");
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");

		Shiti bean = shitiDao.selectBean(" where id= "+ request.getParameter("id"));
		
		bean.setA(a);
		bean.setB(b);
		bean.setC(c);
		bean.setD(d);
		bean.setDaan(daan);
		bean.setNandu(nandu);
		bean.setWenti(wenti);
	
		shitiDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist?kechengid="+bean.getKecheng().getId()+"';</script>");
	}
	
	
//删除选择题操作
	public void shitidelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		
		Shiti bean = shitiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		shitiDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist?kechengid="+bean.getKecheng().getId()+"';</script>");
	}
	
	
	//跳转到查看选择题页面
	public String shitiupdate3() {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("biaoti", "选择题查看");
		this.setUrl("shiti/shitiupdate3.jsp");
		return SUCCESS;
	}
	
	
	
	//判断题列表
	public String shitilist10() {
		HttpServletRequest request = this.getRequest();
		
		
		String kechengid = request.getParameter("kechengid");
		
		request.setAttribute("kechengid", kechengid);
		
		
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		

		if (nandu != null && !"".equals(nandu)) {
			sb.append("nandu like '%" + nandu + "%'");
			sb.append(" and ");
			request.setAttribute("nandu", nandu);
		}
		
		if (wenti != null && !"".equals(wenti)) {
			sb.append("wenti like '%" + wenti + "%'");
			sb.append(" and ");
			request.setAttribute("wenti", wenti);
		}
		
		
		
		sb.append("  leixing='判断题' and deletestatus=0  and kecheng.id="+kechengid+"  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = shitiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", shitiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "shitimethod!shitilist10?kechengid="+kechengid, "共有" + total + "条记录"));
		request.setAttribute("url", "shitimethod!shitilist10?kechengid="+kechengid);
		request.setAttribute("url2", "shitimethod!shiti");
		request.setAttribute("biaoti", "判断题管理");
		this.setUrl("shiti/shitilist10.jsp");
		return SUCCESS;

	}
	
	
//跳转到添加判断题页面
	public String shitiadd10() {
		HttpServletRequest request = this.getRequest();
		String kechengid = request.getParameter("kechengid");
		
		Kecheng kecheng = kechengDao.selectBean(" where id= "+kechengid);
		request.setAttribute("kecheng", kecheng);

		request.setAttribute("url", "shitimethod!shitiadd20?kechengid="+kechengid);
		request.setAttribute("biaoti", "判断题添加");
		this.setUrl("shiti/shitiadd10.jsp");
		return SUCCESS;
	}
	
	
//添加判断题操作
	public void shitiadd20() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		
		String daan = request.getParameter("daan");
		String kechengid = request.getParameter("kechengid");
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");

		Shiti bean = new Shiti();

		bean.setDaan(daan);
		bean.setKecheng(kechengDao.selectBean(" where id= "+kechengid));
		bean.setLeixing("判断题");
		bean.setNandu(nandu);
		bean.setWenti(wenti);
		
		
		
		shitiDao.insertBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist10?kechengid="+bean.getKecheng().getId()+"';</script>");
		
	}
//跳转到更新判断题页面
	public String shitiupdate10() {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "shitimethod!shitiupdate20?id="+bean.getId());
		request.setAttribute("biaoti", "判断题修改");
		this.setUrl("shiti/shitiupdate10.jsp");
		return SUCCESS;
	}
//更新判断题操作
	public void shitiupdate20() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		
		String daan = request.getParameter("daan");


		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");

		Shiti bean = shitiDao.selectBean(" where id= "+ request.getParameter("id"));
		

		bean.setDaan(daan);
		bean.setNandu(nandu);
		bean.setWenti(wenti);
	
		shitiDao.updateBean(bean);
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist10?kechengid="+bean.getKecheng().getId()+"';</script>");
	}
	
	
//删除判断题操作
	public void shitidelete10() throws IOException {
		HttpServletRequest request = this.getRequest();
		
		PrintWriter writer = this.getPrintWriter();
		
		Shiti bean = shitiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		shitiDao.updateBean(bean);
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist10?kechengid="+bean.getKecheng().getId()+"';</script>");
	
	
	}
	
	
	//跳转到查看判断题页面
	public String shitiupdate30() {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("biaoti", "判断题查看");
		this.setUrl("shiti/shitiupdate30.jsp");
		return SUCCESS;
	}
	
	
	
	//填空题列表
	public String shitilist100() {
		HttpServletRequest request = this.getRequest();
		
		
		String kechengid = request.getParameter("kechengid");
		
		request.setAttribute("kechengid", kechengid);
		
		
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		

		if (nandu != null && !"".equals(nandu)) {
			sb.append("nandu like '%" + nandu + "%'");
			sb.append(" and ");
			request.setAttribute("nandu", nandu);
		}
		
		if (wenti != null && !"".equals(wenti)) {
			sb.append("wenti like '%" + wenti + "%'");
			sb.append(" and ");
			request.setAttribute("wenti", wenti);
		}
		
		
		
		sb.append("  leixing='填空题' and deletestatus=0  and kecheng.id="+kechengid+"  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = shitiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", shitiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "shitimethod!shitilist100?kechengid="+kechengid, "共有" + total + "条记录"));
		request.setAttribute("url", "shitimethod!shitilist100?kechengid="+kechengid);
		request.setAttribute("url2", "shitimethod!shiti");
		request.setAttribute("biaoti", "填空题管理");
		this.setUrl("shiti/shitilist100.jsp");
		return SUCCESS;

	}
	
	
//跳转到添加填空题页面
	public String shitiadd100() {
		HttpServletRequest request = this.getRequest();
		String kechengid = request.getParameter("kechengid");
		
		Kecheng kecheng = kechengDao.selectBean(" where id= "+kechengid);
		request.setAttribute("kecheng", kecheng);

		request.setAttribute("url", "shitimethod!shitiadd200?kechengid="+kechengid);
		request.setAttribute("biaoti", "填空题添加");
		this.setUrl("shiti/shitiadd100.jsp");
		return SUCCESS;
	}
	
	
//添加填空题操作
	public void shitiadd200() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		
		String daan = request.getParameter("daan");
		String kechengid = request.getParameter("kechengid");
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");

		Shiti bean = new Shiti();

		bean.setDaan(daan);
		bean.setKecheng(kechengDao.selectBean(" where id= "+kechengid));
		bean.setLeixing("填空题");
		bean.setNandu(nandu);
		bean.setWenti(wenti);
		
		
		
		shitiDao.insertBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist100?kechengid="+bean.getKecheng().getId()+"';</script>");
		
		
	}
	
	
//跳转到更新填空题页面
	public String shitiupdate100() {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "shitimethod!shitiupdate200?id="+bean.getId());
		request.setAttribute("biaoti", "填空题修改");
		this.setUrl("shiti/shitiupdate100.jsp");
		return SUCCESS;
	}
	
	
//更新填空题操作
	public void shitiupdate200() throws IOException {
		HttpServletRequest request = this.getRequest();
		
		PrintWriter writer = this.getPrintWriter();
		
		String daan = request.getParameter("daan");


		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");

		Shiti bean = shitiDao.selectBean(" where id= "+ request.getParameter("id"));
		

		bean.setDaan(daan);
		bean.setNandu(nandu);
		bean.setWenti(wenti);
	
		shitiDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist100?kechengid="+bean.getKecheng().getId()+"';</script>");
	}
	
	
//删除填空题操作
	public void shitidelete100() throws IOException {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		shitiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist100?kechengid="+bean.getKecheng().getId()+"';</script>");
	}
	
	
	//跳转到查看填空题页面
	public String shitiupdate300() {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("biaoti", "填空题查看");
		this.setUrl("shiti/shitiupdate300.jsp");
		return SUCCESS;
	}
	
	
	//问答题列表
	public String shitilist1000() {
		HttpServletRequest request = this.getRequest();
		
		
		String kechengid = request.getParameter("kechengid");
		
		request.setAttribute("kechengid", kechengid);
		
		
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		

		if (nandu != null && !"".equals(nandu)) {
			sb.append("nandu like '%" + nandu + "%'");
			sb.append(" and ");
			request.setAttribute("nandu", nandu);
		}
		
		if (wenti != null && !"".equals(wenti)) {
			sb.append("wenti like '%" + wenti + "%'");
			sb.append(" and ");
			request.setAttribute("wenti", wenti);
		}
		
		
		
		sb.append("  leixing='问答题' and deletestatus=0  and kecheng.id="+kechengid+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = shitiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", shitiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "shitimethod!shitilist1000?kechengid="+kechengid, "共有" + total + "条记录"));
		request.setAttribute("url", "shitimethod!shitilist1000?kechengid="+kechengid);
		request.setAttribute("url2", "shitimethod!shiti");
		request.setAttribute("biaoti", "问答题管理");
		this.setUrl("shiti/shitilist1000.jsp");
		return SUCCESS;

	}
	
	
//跳转到添加问答题页面
	public String shitiadd1000() {
		HttpServletRequest request = this.getRequest();
		String kechengid = request.getParameter("kechengid");
		
		Kecheng kecheng = kechengDao.selectBean(" where id= "+kechengid);
		request.setAttribute("kecheng", kecheng);

		request.setAttribute("url", "shitimethod!shitiadd2000?kechengid="+kechengid);
		request.setAttribute("biaoti", "问答题添加");
		this.setUrl("shiti/shitiadd1000.jsp");
		return SUCCESS;
	}
	
	
//添加问答题操作
	public void shitiadd2000() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		
		String daan = request.getParameter("daan");
		String kechengid = request.getParameter("kechengid");
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");

		Shiti bean = new Shiti();
		bean.setDaan(daan);
		bean.setKecheng(kechengDao.selectBean(" where id= "+kechengid));
		bean.setLeixing("问答题");
		bean.setNandu(nandu);
		bean.setWenti(wenti);
		
		
		
		shitiDao.insertBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist1000?kechengid="+bean.getKecheng().getId()+"';</script>");
		
		
	}
//跳转到更新问答题页面
	public String shitiupdate1000() {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "shitimethod!shitiupdate2000?id="+bean.getId());
		request.setAttribute("biaoti", "问答题修改");
		this.setUrl("shiti/shitiupdate1000.jsp");
		return SUCCESS;
	}
//更新问答题操作
	public void shitiupdate2000() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		
		String daan = request.getParameter("daan");


		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");

		Shiti bean = shitiDao.selectBean(" where id= "+ request.getParameter("id"));
		

		bean.setDaan(daan);
		bean.setNandu(nandu);
		bean.setWenti(wenti);
	
		shitiDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist1000?kechengid="+bean.getKecheng().getId()+"';</script>");
	
	}
	
	
//删除问答题操作
	public void shitidelete1000() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		
		Shiti bean = shitiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		shitiDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shitimethod!shitilist1000?kechengid="+bean.getKecheng().getId()+"';</script>");
	
	}
	
	
	//跳转到查看问答题页面
	public String shitiupdate3000() {
		HttpServletRequest request = this.getRequest();
		Shiti bean = shitiDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("biaoti", "问答题查看");
		this.setUrl("shiti/shitiupdate3000.jsp");
		return SUCCESS;
	}
	
	
	
	//试题库查询
	public String shitilist2() {
		HttpServletRequest request = this.getRequest();
		
		
		
		String mingchen = request.getParameter("mingchen");
		
		String nandu = request.getParameter("nandu");
		String wenti = request.getParameter("wenti");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (mingchen != null && !"".equals(mingchen)) {
			sb.append(" kecheng.mingchen like '%" + mingchen + "%'");
			sb.append(" and ");
			request.setAttribute("mingchen", mingchen);
		}

		if (nandu != null && !"".equals(nandu)) {
			sb.append("nandu like '%" + nandu + "%'");
			sb.append(" and ");
			request.setAttribute("nandu", nandu);
		}
		
		if (wenti != null && !"".equals(wenti)) {
			sb.append("wenti like '%" + wenti + "%'");
			sb.append(" and ");
			request.setAttribute("wenti", wenti);
		}
		
		
	
		
		sb.append("   deletestatus=0  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = shitiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", shitiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "shitimethod!shitilist2", "共有" + total + "条记录"));
		request.setAttribute("url", "shitimethod!shitilist2");
		request.setAttribute("url2", "shitimethod!shiti");
		request.setAttribute("biaoti", "试题库查询");
		this.setUrl("shiti/shitilist2.jsp");
		return SUCCESS;

	}
	
	
	
	
}
