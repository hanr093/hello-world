package action;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Exam;
import model.Mingxi;
import model.User;
import util.Pager;
import dao.ExamDao;
import dao.MingxiDao;


public class MingxiAction extends BaseAction{


	private static final long serialVersionUID  = -4304509122548259589L;

	private MingxiDao mingxiDao;


	public MingxiDao getMingxiDao() {
		return mingxiDao;
	}

	public void setMingxiDao(MingxiDao mingxiDao) {
		this.mingxiDao = mingxiDao;
	}


	private ExamDao examDao;


	public ExamDao getExamDao() {
		return examDao;
	}

	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}



	//阅卷题目管理
	public String mingxilist() {
		HttpServletRequest request = this.getRequest();


		Exam exam = examDao.selectBean(" where id= "+request.getParameter("id") );
		if(exam!=null){
			HttpSession session  = request.getSession();
			User user = (User) session.getAttribute("user");
			exam.setYuejuanren(user);
			exam.setZhuangtai("已阅卷");
			examDao.updateBean(exam);
			request.setAttribute("examid", exam.getId());

			String where = " where exam.id="+exam.getId()+" order by shifougeifen desc";
			int currentpage = 1;
			int pagesize = 15;
			if (request.getParameter("pagenum") != null) {
				currentpage = Integer.parseInt(request.getParameter("pagenum"));

			}


			request.setAttribute("list", mingxiDao.selectBeanList((currentpage - 1)
					* pagesize, pagesize, where));



			int total = mingxiDao.selectBeanCount(where.replaceAll("order by shifougeifen desc", ""));
			request.setAttribute("list", mingxiDao.selectBeanList((currentpage - 1)
					* pagesize, pagesize, where));
			String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "mingximethod!mingxilist?id="+exam.getId(), "一共有"+total+"条记录");
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("title", "阅卷");
			request.setAttribute("url", "mingximethod!mingxilist?id="+exam.getId());
			this.setUrl("mingxi/mingxilist.jsp");
			return SUCCESS;


		}else{
			return null;
		}

	}

	//跳转到评分页面
	public String mingxiupdate() {
		HttpServletRequest request = this.getRequest();
		Mingxi bean = mingxiDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		String examid = request.getParameter("examid");
		request.setAttribute("examid",examid );

		request.setAttribute("url", "mingximethod!mingxiupdate2?id="+bean.getId()+"&examid="+examid);
		request.setAttribute("biaoti", "阅卷评分");

		this.setUrl("mingxi/mingxiupdate.jsp");
		return SUCCESS;
	}
//	评分操作
	public void mingxiupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String examid = request.getParameter("examid");
		Exam exam = examDao.selectBean(" where id= "+examid);
		String defen = request.getParameter("defen");
		String dianping = request.getParameter("dianping");

		Mingxi bean = mingxiDao.selectBean(" where id = "+request.getParameter("id"));
		
		
		if(bean.getShijuanitem().getFenzhi()<Double.parseDouble(defen)){
			
			
			writer.print("<script language=javascript>alert('不能超过题目的分数，操作失败');window.location.href='mingximethod!mingxilist?id="+examid+"';</script>");
		
			return ;
		}
		
		exam.setChengji2(exam.getChengji2()+Double.parseDouble(defen)-bean.getDefen());
		examDao.updateBean(exam);
		bean.setDefen(Double.parseDouble(defen));
		bean.setDianping(dianping);
		bean.setShifougeifen("已给分");
		mingxiDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='mingximethod!mingxilist?id="+examid+"';</script>");

	}


	//阅卷明细查询
	public String mingxilist2() {
		HttpServletRequest request = this.getRequest();
		Exam exam = examDao.selectBean(" where id= "+request.getParameter("id") );


		request.setAttribute("examid", exam.getId());

		String where = " where exam.id="+exam.getId()+" order by shifougeifen desc";
		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));

		}


		request.setAttribute("list", mingxiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));



		int total = mingxiDao.selectBeanCount(where.replaceAll("order by shifougeifen desc", ""));
		request.setAttribute("list", mingxiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		String pagerinfo = Pager.getPagerNormal((int)total, pagesize, currentpage, "mingximethod!mingxilist2?id="+exam.getId(), "一共有"+total+"条记录");
		request.setAttribute("pagerinfo", pagerinfo);
		request.setAttribute("title", "阅卷明细查询");
		request.setAttribute("url", "mingximethod!mingxilist2?id="+exam.getId());
		this.setUrl("mingxi/mingxilist2.jsp");
		return SUCCESS;



	}

	
	

	
	



}
