package action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Kecheng;
import model.Shijuan;
import model.Shijuanitem;
import model.Shiti;
import util.Arith;
import util.Pager;
import util.Util;
import dao.KechengDao;
import dao.ShijuanDao;
import dao.ShijuanitemDao;
import dao.ShitiDao;


public class ShijuanAction extends BaseAction{
	
	
	private static final long serialVersionUID  = -4304509122548259589L;
	
	private ShijuanDao shijuanDao;
	
	public ShijuanDao getShijuanDao() {
		return shijuanDao;
	}

	public void setShijuanDao(ShijuanDao shijuanDao) {
		this.shijuanDao = shijuanDao;
	}
	
	
	private KechengDao kechengDao;


	public KechengDao getKechengDao() {
		return kechengDao;
	}

	public void setKechengDao(KechengDao kechengDao) {
		this.kechengDao = kechengDao;
	}

	
	
	//试卷列表
	public String shijuanlist()  {
		HttpServletRequest request = this.getRequest();
		
		String juanming = request.getParameter("juanming");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		if(juanming!=null&&!"".equals(juanming)){
			sb.append(" juanming like '%"+juanming+"%'");
			sb.append(" and ");


			request.setAttribute("juanming", juanming);
		}
	
		sb.append(" deletestatus=0   order by id desc");
		String where = sb.toString();

		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = shijuanDao.selectBeanCount(where.replace("order by id desc", ""));
		request.setAttribute("list", shijuanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "shijuanmethod!shijuanlist", "共有" + total + "条记录"));
		request.setAttribute("url", "shijuanmethod!shijuanlist");
		request.setAttribute("url2", "shijuanmethod!shijuan");

		this.setUrl("shijuan/shijuanlist.jsp");
		return SUCCESS;
	}

//跳转到添加试卷页面
	public String shijuanadd() {
		HttpServletRequest request = this.getRequest();
		
		request.setAttribute("kechenglist", kechengDao.selectBeanList(0, 9999, " where deletestatus=0 "));
		request.setAttribute("url", "shijuanmethod!shijuanadd2");

		this.setUrl("shijuan/shijuanadd.jsp");
		return SUCCESS;
	}
//添加试卷操作
	public void shijuanadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String examtime = request.getParameter("examtime");
		String juanming = request.getParameter("juanming");
		String kechengid = request.getParameter("kechengid");
		String nandu = request.getParameter("nandu");
		
		Shijuan bean = new Shijuan();
		
		bean.setCtime(Util.getTime());
		bean.setExamtime(examtime);
		bean.setJuanming(juanming);
		bean.setKecheng(kechengDao.selectBean(" where id= "+kechengid));
		bean.setNandu(nandu);
		bean.setZhuangtai("未使用");
		
		shijuanDao.insertBean(bean);
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shijuanmethod!shijuanlist';</script>");

		
	}
//跳转到更新试卷页面
	public String shijuanupdate() {
		HttpServletRequest request = this.getRequest();
		

		Shijuan bean = shijuanDao.selectBean(" where id= "+request.getParameter("id"));
		
		request.setAttribute("url", "shijuanmethod!shijuanupdate2?id="+bean.getId());
		
		request.setAttribute("bean", bean);
		this.setUrl("shijuan/shijuanupdate.jsp");
		return SUCCESS;
	}
//更新试卷操作
	public void shijuanupdate2() throws IOException {
		
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String examtime = request.getParameter("examtime");
		String juanming = request.getParameter("juanming");
		String nandu = request.getParameter("nandu");
		
		
		Shijuan bean = shijuanDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setExamtime(examtime);
		bean.setJuanming(juanming);
		bean.setNandu(nandu);
		
		shijuanDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shijuanmethod!shijuanlist';</script>");
		
	}

//查看试卷操作
	public String shijuanupdate3() {
		HttpServletRequest request = this.getRequest();

		Shijuan bean = shijuanDao.selectBean(" where id= "+request.getParameter("id"));
		
		request.setAttribute("bean", bean);
		
		this.setUrl("shijuan/shijuanupdate3.jsp");
		return SUCCESS;
	}
//删除试卷操作
	public void shijuandelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		Shijuan bean = shijuanDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setDeletestatus(1);
		
		shijuanDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shijuanmethod!shijuanlist';</script>");
	}
	
	//使用试卷操作
	public void shijuandelete2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		Shijuan bean = shijuanDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setZhuangtai("使用中");
		
		shijuanDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shijuanmethod!shijuanlist';</script>");
	}
	
	//使用试卷操作
	public void shijuandelete3() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		Shijuan bean = shijuanDao.selectBean(" where id= "+request.getParameter("id"));
		
		bean.setZhuangtai("未使用");
		
		shijuanDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='shijuanmethod!shijuanlist';</script>");
	}
	
	
	
	
	//跳转到开始组卷
	public String shijuanupdate5() {
		HttpServletRequest request = this.getRequest();

		Shijuan bean = shijuanDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
	
		
		
		request.setAttribute("bean", bean);
		request.setAttribute("url", "shijuanmethod!shijuanupdate6?id="+bean.getId());
		request.setAttribute("biaoti", "定制组卷规则");
		this.setUrl("shijuan/shijuanupdate5.jsp");
		return SUCCESS;
	}
	
	
	private ShijuanitemDao shijuanitemDao;

	public ShijuanitemDao getShijuanitemDao() {
		return shijuanitemDao;
	}

	public void setShijuanitemDao(ShijuanitemDao shijuanitemDao) {
		this.shijuanitemDao = shijuanitemDao;
	}

	
	private ShitiDao shitiDao;

	public ShitiDao getShitiDao() {
		return shitiDao;
	}

	public void setShitiDao(ShitiDao shitiDao) {
		this.shitiDao = shitiDao;
	}
	
	

	//num随机的数量
	private static List<Shiti> suiji(List<Shiti> list,int num){
		Collections.shuffle(list);
		List<Shiti> list2 = new ArrayList<Shiti>();
		if(list.size()<=num){
			num = list.size();
		}
		for(int i=0;i<num;i++){
			list2.add(list.get(i));
		}
		return list2;
	}
	
	

	@SuppressWarnings("static-access")
	public void shijuanupdate6() throws IOException {
		HttpServletRequest request = this.getRequest();

		PrintWriter writer = this.getPrintWriter();
		
		
		
		Shijuan bean = shijuanDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		
		List<Shijuanitem> sjlist = shijuanitemDao.selectBeanList(0, 9999, " where shijuan.id= "+bean.getId());
		for(Shijuanitem sji:sjlist){
			sji.setDeletestatus(1);
			shijuanitemDao.updateBean(sji);
		}
		
		Kecheng kecheng = bean.getKecheng();
		
		
		String xzt1 = request.getParameter("xzt1");
		String xzt2 = request.getParameter("xzt2");
		String xzt3 = request.getParameter("xzt3");
		String xzt4 = request.getParameter("xzt4");//每道题分值

		
		String pdt1 = request.getParameter("pdt1");
		String pdt2 = request.getParameter("pdt2");
		String pdt3 = request.getParameter("pdt3");
		String pdt4 = request.getParameter("pdt4");

		
		String tkt1 = request.getParameter("tkt1");
		String tkt2 = request.getParameter("tkt2");
		String tkt3 = request.getParameter("tkt3");
		String tkt4 = request.getParameter("tkt4");

		
		String wdt1 = request.getParameter("wdt1");
		String wdt2 = request.getParameter("wdt2");
		String wdt3 = request.getParameter("wdt3");
		String wdt4 = request.getParameter("wdt4");

		
		int cxzt1 = Integer.parseInt(xzt1);
		int cxzt2 = Integer.parseInt(xzt2);
		int cxzt3 = Integer.parseInt(xzt3);
		
		int cpdt1 = Integer.parseInt(pdt1);
		int cpdt2 = Integer.parseInt(pdt2);
		int cpdt3 = Integer.parseInt(pdt3);
		
		int ctkt1 = Integer.parseInt(tkt1);
		int ctkt2 = Integer.parseInt(tkt2);
		int ctkt3 = Integer.parseInt(tkt3);
		
		int cwdt1 = Integer.parseInt(wdt1);
		int cwdt2 = Integer.parseInt(wdt2);
		int cwdt3 = Integer.parseInt(wdt3);
		
		
		
		int ccxzt1 = shitiDao.selectBeanCount(" where deletestatus=0 and  leixing='选择题'  and nandu='简单' and kecheng.id= "+kecheng.getId());
		int ccxzt2 = shitiDao.selectBeanCount(" where deletestatus=0 and  leixing='选择题'  and nandu='中等' and kecheng.id= "+kecheng.getId());
		int ccxzt3 = shitiDao.selectBeanCount(" where deletestatus=0 and  leixing='选择题'  and nandu='复杂' and kecheng.id= "+kecheng.getId());
		
		int ccpdt1 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='判断题' and nandu='简单' and kecheng.id= "+kecheng.getId());
		int ccpdt2 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='判断题' and nandu='中等' and kecheng.id= "+kecheng.getId());
		int ccpdt3 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='判断题' and nandu='复杂' and kecheng.id= "+kecheng.getId());
		
		int cctkt1 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='填空题' and nandu='简单'  and kecheng.id= "+kecheng.getId());
		int cctkt2 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='填空题' and nandu='中等'  and kecheng.id= "+kecheng.getId());
		int cctkt3 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='填空题' and nandu='复杂'  and kecheng.id= "+kecheng.getId());
		
		int ccwdt1 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='问答题' and nandu='简单' and kecheng.id= "+kecheng.getId());
		int ccwdt2 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='问答题' and nandu='中等' and kecheng.id= "+kecheng.getId());
		int ccwdt3 = shitiDao.selectBeanCount(" where deletestatus=0 and leixing='问答题' and nandu='复杂' and kecheng.id= "+kecheng.getId());
		
		
		//判断组卷的题目数量和题库中的数量比较，不够则提示组卷失败
		if(cxzt1>ccxzt1){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为简单的选择题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		if(cxzt2>ccxzt2){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为中等的选择题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		if(cxzt3>ccxzt3){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为复杂的选择题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		
		
		
		if(cpdt1>ccpdt1){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为简单的判断题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		if(cpdt2>ccpdt2){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为中等的判断题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		
		if(cpdt3>ccpdt3){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为复杂的判断题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		
		
		if(ctkt1>cctkt1){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为简单的填空数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		if(ctkt2>cctkt2){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为中等的填空数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		if(ctkt3>cctkt3){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为复杂的填空数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		
		
		if(cwdt1>ccwdt1){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为简单的问答题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		
		if(cwdt2>ccwdt2){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为中等的问答题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		
		if(cwdt3>ccwdt3){
			writer.print(
					"<script language=javascript>alert('题库中课程为"+kecheng.getMingchen()+",难度为复杂的问答题数量不够，组卷失败');" +
							"window.location.href='shijuanmethod!shijuanupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		
		
		
		//组卷操作
		bean.setZongfen(0);
		
		
		List<Shiti> list = new ArrayList<Shiti>();
		
		List<Shiti> xzt1list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='选择题'  and nandu='简单' and kecheng.id= "+kecheng.getId());
		List<Shiti> xzt2list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='选择题'  and nandu='中等' and kecheng.id= "+kecheng.getId());
		List<Shiti> xzt3list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='选择题'  and nandu='复杂' and kecheng.id= "+kecheng.getId());
		List<Shiti> pdt1list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='判断题'  and nandu='简单' and kecheng.id= "+kecheng.getId());
		List<Shiti> pdt2list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='判断题'  and nandu='中等' and kecheng.id= "+kecheng.getId());
		List<Shiti> pdt3list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='判断题'  and nandu='复杂' and kecheng.id= "+kecheng.getId());
		List<Shiti> tkt1list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='填空题'  and nandu='简单' and kecheng.id= "+kecheng.getId());
		List<Shiti> tkt2list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='填空题'  and nandu='中等' and kecheng.id= "+kecheng.getId());
		List<Shiti> tkt3list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='填空题'  and nandu='复杂' and kecheng.id= "+kecheng.getId());
		List<Shiti> wdt1list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='问答题'  and nandu='简单' and kecheng.id= "+kecheng.getId());
		List<Shiti> wdt2list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='问答题'  and nandu='中等' and kecheng.id= "+kecheng.getId());
		List<Shiti> wdt3list = shitiDao.selectBeanList(0, 9999, " where deletestatus=0 and leixing='问答题'  and nandu='复杂' and kecheng.id= "+kecheng.getId());
		
		

		List<Shiti> xztlist1 =  this.suiji(xzt1list, cxzt1);
		
		for(Shiti s:xztlist1){
			list.add(s);
		}
		
		

		List<Shiti> xztlist2 =  this.suiji(xzt2list, cxzt2);
		
		for(Shiti s:xztlist2){
			list.add(s);
		}
		
		

		List<Shiti> xztlist3 =  this.suiji(xzt3list, cxzt3);
		
		for(Shiti s:xztlist3){
			list.add(s);
		}
		
		List<Shiti> pdtlist1 =  this.suiji(pdt1list, cpdt1);
		
		for(Shiti s:pdtlist1){
			list.add(s);
		}
		List<Shiti> pdtlist2 =  this.suiji(pdt2list, cpdt2);
		
		for(Shiti s:pdtlist2){
			list.add(s);
		}
		List<Shiti> pdtlist3 =  this.suiji(pdt3list, cpdt3);
		
		for(Shiti s:pdtlist3){
			list.add(s);
		}
		
		List<Shiti> tktlist1 =  this.suiji(tkt1list, ctkt1);
		
		for(Shiti s:tktlist1){
			list.add(s);
		}
		
		List<Shiti> tktlist2 =  this.suiji(tkt2list, ctkt2);
		
		for(Shiti s:tktlist2){
			list.add(s);
		}
		
		List<Shiti> tktlist3 =  this.suiji(tkt3list, ctkt3);
		
		for(Shiti s:tktlist3){
			list.add(s);
		}
		
		List<Shiti> wdtlist1 =  this.suiji(wdt1list, cwdt1);
		
		for(Shiti s:wdtlist1){
			list.add(s);
		}
		
		List<Shiti> wdtlist2 =  this.suiji(wdt2list, cwdt2);
		
		for(Shiti s:wdtlist2){
			list.add(s);
		}
		
		List<Shiti> wdtlist3 =  this.suiji(wdt3list, cwdt3);
		
		for(Shiti s:wdtlist3){
			list.add(s);
		}
		
		
		for(Shiti st:list){
			Shijuanitem si = new Shijuanitem();
			if("选择题".equals(st.getLeixing())){
				si.setFenzhi(Double.parseDouble(xzt4));
			}else if("判断题".equals(st.getLeixing())){
				si.setFenzhi(Double.parseDouble(pdt4));
			}else if("填空题".equals(st.getLeixing())){
				si.setFenzhi(Double.parseDouble(tkt4));
			}else if("问答题".equals(st.getLeixing())){
				si.setFenzhi(Double.parseDouble(wdt4));
			}
			si.setShijuan(bean);
			si.setShiti(st);
			shijuanitemDao.insertBean(si);
			bean.setZongfen(Arith.add(si.getFenzhi(), bean.getZongfen()));
		}
		shijuanDao.updateBean(bean);
		
		
		

		writer.print(
				"<script language=javascript>alert('完成自动组卷');" +
						"window.location.href='shijuanmethod!shijuanlist';</script>");
		
		
		
	}
	
	
	//试卷列表
	public String shijuanlist2()  {
		HttpServletRequest request = this.getRequest();
		
		String juanming = request.getParameter("juanming");
		
		String mingchen = request.getParameter("mingchen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		if(juanming!=null&&!"".equals(juanming)){
			sb.append(" juanming like '%"+juanming+"%'");
			sb.append(" and ");


			request.setAttribute("juanming", juanming);
		}
		
		if(mingchen!=null&&!"".equals(mingchen)){
			sb.append(" kecheng.mingchen like '%"+mingchen+"%'");
			sb.append(" and ");


			request.setAttribute("mingchen", mingchen);
		}
	
		sb.append(" deletestatus=0  and zhuangtai='使用中'  order by id desc");
		String where = sb.toString();

		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = shijuanDao.selectBeanCount(where.replace("order by id desc", ""));
		request.setAttribute("list", shijuanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "shijuanmethod!shijuanlist2", "共有" + total + "条记录"));
		request.setAttribute("url", "shijuanmethod!shijuanlist2");
		request.setAttribute("url2", "shijuanmethod!shijuan");

		this.setUrl("shijuan/shijuanlist2.jsp");
		return SUCCESS;
	}
	
}
