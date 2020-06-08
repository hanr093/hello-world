package action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Exam;
import model.Mingxi;
import model.Shijuan;
import model.Shijuanitem;
import model.User;

import util.Pager;
import util.Util;
import dao.ExamDao;
import dao.MingxiDao;
import dao.ShijuanDao;
import dao.ShijuanitemDao;


public class ExamAction extends BaseAction{
	
	
	private static final long serialVersionUID  = -4304509122548259589L;
	
	private ExamDao examDao;

	public ExamDao getExamDao() {
		return examDao;
	}

	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}

	
	
	private ShijuanDao shijuanDao;
	
	
	public ShijuanDao getShijuanDao() {
		return shijuanDao;
	}

	public void setShijuanDao(ShijuanDao shijuanDao) {
		this.shijuanDao = shijuanDao;
	}
	
	
	private MingxiDao mingxiDao;


	public MingxiDao getMingxiDao() {
		return mingxiDao;
	}

	public void setMingxiDao(MingxiDao mingxiDao) {
		this.mingxiDao = mingxiDao;
	}
	
	private ShijuanitemDao shijuanitemDao;
	
	
	public ShijuanitemDao getShijuanitemDao() {
		return shijuanitemDao;
	}

	public void setShijuanitemDao(ShijuanitemDao shijuanitemDao) {
		this.shijuanitemDao = shijuanitemDao;
	}

	
	
	//跳转到考试页面
	public String exam() throws IOException {
		HttpServletRequest request = this.getRequest();
	
		PrintWriter writer = this.getPrintWriter();
		
    	String shijuanid = request.getParameter("shijuanid");
		
    	Shijuan bean = shijuanDao.selectBean(" where id= "+shijuanid);
    	
    	
    	
    	
    	HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		Exam exam = examDao.selectBean(" where shijuan.id ="+bean.getId()+" and user.id= "+user.getId());
    	
		if(exam==null){
			List<Shijuanitem> list1 = 
				shijuanitemDao.selectBeanList(0, 9999, " where deletestatus=0 and shiti.leixing='选择题'  and shijuan.id="+bean.getId());
				
				List<Shijuanitem> list2 = 
					shijuanitemDao.selectBeanList(0, 9999, " where deletestatus=0 and shiti.leixing='判断题'  and shijuan.id="+bean.getId());
				
				List<Shijuanitem> list3 = 
					shijuanitemDao.selectBeanList(0, 9999, " where deletestatus=0 and shiti.leixing='填空题'  and shijuan.id="+bean.getId());
				
				List<Shijuanitem> list4 = 
					shijuanitemDao.selectBeanList(0, 9999, " where deletestatus=0 and shiti.leixing='问答题'  and shijuan.id="+bean.getId());
				
					
				request.setAttribute("shuliang1", list1.size());
				request.setAttribute("shuliang2", list2.size());
				request.setAttribute("shuliang3", list3.size());
				request.setAttribute("shuliang4", list4.size());
				request.setAttribute("list1", list1);
				request.setAttribute("list2", list2);
				request.setAttribute("list3", list3);
				request.setAttribute("list4", list4);
				request.setAttribute("examtime", bean.getExamtime());
				request.setAttribute("kecheng", bean.getKecheng().getMingchen());
				request.setAttribute("shijuanid", bean.getId());	
				this.setUrl("exam/exam.jsp");
				return SUCCESS;
		}else{
			writer.print("<script language=javascript>alert('你已经考过该试卷，不可重复考试');window.location.href='shijuanmethod!shijuanlist2';</script>");

			return null;
		}
	}
	
	
	

	//提交试卷页面
	public void exam2() throws IOException {
		HttpServletRequest request = this.getRequest();
		String xuanzetishuliang = request.getParameter("xuanzetishuliang");
		String panduantishuliang = request.getParameter("panduantishuliang");
		String tiankongtishuliang = request.getParameter("tiankongtishuliang");
		String wendantishuliang = request.getParameter("wendantishuliang");
		String bianhao = Util.getbianhao( examDao.selectBeanCount("") );
			
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		String shijuanid = request.getParameter("shijuanid");
		
		Shijuan shijuan = null;
		if(shijuanid!=null&&!"".equals(shijuanid)){
			shijuan = shijuanDao.selectBean(" where id= "+shijuanid);
		}
		
		Exam bean = new Exam();
		
		bean.setCreatetime(Util.getTime());
		bean.setBianhao(bianhao);
		bean.setUser(user);
		bean.setShijuan(shijuan);
		bean.setZhuangtai("未阅卷");
		
		examDao.insertBean(bean);
		
		for(int i=0;i<Integer.parseInt(xuanzetishuliang);i++){
			
			Mingxi mingxi = new Mingxi();
			mingxi.setCreatetime(Util.getTime());
			
			mingxi.setShijuanitem(shijuanitemDao.selectBean(" where id= "+request.getParameter("xuanzetiid"+i)));

			mingxi.setUser(user);
			mingxi.setWodedaan(request.getParameter("xuanzetidaan"+i));
			mingxi.setShifougeifen("未给分");
			
			mingxi.setExam(bean);
			
			mingxiDao.insertBean(mingxi);
		}
		
	
		for(int i=0;i<Integer.parseInt(panduantishuliang);i++){
			Mingxi mingxi = new Mingxi();
			mingxi.setCreatetime(Util.getTime());
			mingxi.setExam(bean);
			mingxi.setShijuanitem(shijuanitemDao.selectBean(" where id= "+request.getParameter("panduantiid"+i)));
			mingxi.setUser(user);
			mingxi.setWodedaan(request.getParameter("panduantidaan"+i));
			mingxi.setShifougeifen("未给分");
			mingxiDao.insertBean(mingxi);
		}
		

		for(int i=0;i<Integer.parseInt(tiankongtishuliang);i++){

			Mingxi mingxi = new Mingxi();
			mingxi.setCreatetime(Util.getTime());
			mingxi.setExam(bean);
			mingxi.setShijuanitem(shijuanitemDao.selectBean(" where id= "+request.getParameter("tiankongtiid"+i)));
			mingxi.setUser(user);
			mingxi.setWodedaan(request.getParameter("tiankongtidaan"+i));
			mingxi.setShifougeifen("未给分");
			mingxiDao.insertBean(mingxi);
		}
		

		for(int i=0;i<Integer.parseInt(wendantishuliang);i++){

			Mingxi mingxi = new Mingxi();
			mingxi.setCreatetime(Util.getTime());
			mingxi.setExam(bean);
			mingxi.setShijuanitem(shijuanitemDao.selectBean(" where id= "+request.getParameter("wendatiid"+i)));
			mingxi.setUser(user);
			mingxi.setWodedaan(request.getParameter("wendatidaan"+i));
			mingxi.setShifougeifen("未给分");
			mingxiDao.insertBean(mingxi);
		}
		
		
		List<Mingxi> list1 = mingxiDao.selectBeanList(0, 9999, " where  exam.id="+bean.getId()+" and shijuanitem.shiti.leixing='选择题' ");
		
		
		int zhengque1 = 0;
		for(Mingxi mingxi:list1){
			if(mingxi.getShijuanitem().getShiti().getDaan().equals(mingxi.getWodedaan())){
				zhengque1++;	
				bean.setChengji2(bean.getChengji2()+mingxi.getShijuanitem().getFenzhi());
				mingxi.setDefen(mingxi.getShijuanitem().getFenzhi());
				
			}
			mingxi.setShifougeifen("已给分");
			mingxiDao.updateBean(mingxi);	
		}
		request.setAttribute("zhengque1", "共有"+list1.size()+"道选择题，正确的数目"+zhengque1+"。");
		request.setAttribute("list1", list1);
		List<Mingxi> list2 =  mingxiDao.selectBeanList(0, 9999, " where  exam.id="+bean.getId()+" and shijuanitem.shiti.leixing='判断题' ");
		int zhengque2 = 0;
		for(Mingxi mingxi:list2){
			if(mingxi.getShijuanitem().getShiti().getDaan().equals(mingxi.getWodedaan())){
				zhengque2++;
				bean.setChengji2(bean.getChengji2()+mingxi.getShijuanitem().getFenzhi());
				mingxi.setDefen(mingxi.getShijuanitem().getFenzhi());
				
			}
			mingxi.setShifougeifen("已给分");
			mingxiDao.updateBean(mingxi);	
		}
		
		
		bean.setChengji("共有"+list1.size()+"道选择题，正确的题目数"+zhengque1+"，共有"+list2.size()+"道判断题，正确的题目数"+zhengque2);
		
		
	
		
		examDao.updateBean(bean);
		
		PrintWriter writer = this.getPrintWriter();
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='exammethod!examlist';</script>");
		
		
	}
	
	
	//考试成绩查询
	public String examlist() {
		HttpServletRequest request = this.getRequest();
		
		String bianhao = request.getParameter("bianhao");


    	StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if(bianhao!=null&&!"".equals(bianhao)){
			sb.append(" bianhao like '%"+bianhao+"%'");
			sb.append(" and ");
	
			request.setAttribute("bianhao", bianhao);
		}
	
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");

		sb.append(" user.id="+user.getId()+" order by id desc");

		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
			
		}

		int total = examDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", examDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "exammethod!examlist", "一共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		request.setAttribute("title", "考试成绩查询");
		request.setAttribute("url", "exammethod!examlist");
		this.setUrl("exam/examlist.jsp");
		return SUCCESS;
		
	}
	
	
	//查看试卷
	public String examupdate() throws IOException {
		HttpServletRequest request = this.getRequest();
		Exam exam = examDao.selectBean(" where id= "+request.getParameter("id"));

		
		
		
		List<Mingxi> list1 = mingxiDao.selectBeanList(0, 9999, " where  exam.id="+exam.getId()+" and shijuanitem.shiti.leixing='选择题' ");
		int zhengque1 = 0;
		for(Mingxi bean:list1){
			if(bean.getShijuanitem().getShiti().getDaan().equals(bean.getWodedaan()))
				zhengque1++;
		}
		request.setAttribute("zhengque1", "共有"+list1.size()+"道选择题，正确的数目"+zhengque1);
		request.setAttribute("list1", list1);
		List<Mingxi> list2 =  mingxiDao.selectBeanList(0, 9999, " where  exam.id="+exam.getId()+" and shijuanitem.shiti.leixing='判断题' ");
		int zhengque2 = 0;
		for(Mingxi bean:list2){
			if(bean.getShijuanitem().getShiti().getDaan().equals(bean.getWodedaan()))
				zhengque2++;
		}
		request.setAttribute("zhengque2", "共有"+list2.size()+"道判断题，正确的数目"+zhengque2);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", mingxiDao.selectBeanList(0, 9999, " where  exam.id="+exam.getId()+" and shijuanitem.shiti.leixing='填空题' "));
		request.setAttribute("list4", mingxiDao.selectBeanList(0, 9999, " where  exam.id="+exam.getId()+" and shijuanitem.shiti.leixing='问答题' "));
		
		this.setUrl("exam/examupdate.jsp");
		return SUCCESS;
	}
	
	
	//阅卷管理
	public String examlist2() {
		HttpServletRequest request = this.getRequest();

		String xingming = request.getParameter("xingming");

    	StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if(xingming!=null&&!"".equals(xingming)){
			sb.append(" user.xingming like '%"+xingming+"%'");
			sb.append(" and ");

			request.setAttribute("xingming", xingming);
		}
		
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");

		sb.append(" zhuangtai='未阅卷'  and shijuan.kecheng.id="+user.getKecheng().getId()+" order by id desc");
		
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
			
		}

		
		int total = examDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", examDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "exammethod!examlist2", "一共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		request.setAttribute("url", "exammethod!examlist2");
		this.setUrl("exam/examlist2.jsp");
		return SUCCESS;

	}
	
	
	//阅卷管理
	public String examlist3() {
		HttpServletRequest request = this.getRequest();

		String bianhao = request.getParameter("bianhao");

    	StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if(bianhao!=null&&!"".equals(bianhao)){
			sb.append(" bianhao like '%"+bianhao+"%'");
			sb.append(" and ");

			request.setAttribute("bianhao", bianhao);
		}
		
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");

		sb.append(" zhuangtai='已阅卷'  and yuejuanren.id="+user.getId()+" order by id desc");
		
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
			
		}

		
		int total = examDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", examDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "exammethod!examlist3", "一共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		request.setAttribute("url", "exammethod!examlist3");
		this.setUrl("exam/examlist3.jsp");
		return SUCCESS;

	}
	
	//阅卷管理
	public String examlist4() {
		HttpServletRequest request = this.getRequest();

		String bianhao = request.getParameter("bianhao");

    	StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if(bianhao!=null&&!"".equals(bianhao)){
			sb.append(" bianhao like '%"+bianhao+"%'");
			sb.append(" and ");

			request.setAttribute("bianhao", bianhao);
		}
		
	

		sb.append(" 1=1 order by id desc");
		
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
			
		}

		
		int total = examDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", examDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "exammethod!examlist4", "一共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		request.setAttribute("url", "exammethod!examlist4");
		this.setUrl("exam/examlist4.jsp");
		return SUCCESS;

	}
	
}
