package action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Shijuan;
import model.Shijuanitem;
import model.Shiti;

import org.apache.struts2.ServletActionContext;

import util.Pager;
import dao.ShijuanDao;
import dao.ShijuanitemDao;
import dao.ShitiDao;


public class ShijuanitemAction extends BaseAction{
	
	
	private static final long serialVersionUID  = -4304509122548259589L;
	
	private ShijuanitemDao shijuanitemDao;
	

	
	public ShijuanitemDao getShijuanitemDao() {
		return shijuanitemDao;
	}

	public void setShijuanitemDao(ShijuanitemDao shijuanitemDao) {
		this.shijuanitemDao = shijuanitemDao;
	}

	
	private ShijuanDao shijuanDao;
	

	
	public ShijuanDao getShijuanDao() {
		return shijuanDao;
	}

	public void setShijuanDao(ShijuanDao shijuanDao) {
		this.shijuanDao = shijuanDao;
	}
	


	//试卷详情
	public String shijuanitemlist() {
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");

		request.setAttribute("bean", shijuanDao.selectBean(" where id= "+id));
		
		List<Shijuanitem> list1 = shijuanitemDao.selectBeanList(0, 9999, " where deletestatus=0 and shiti.leixing='选择题' and shijuan.id= "+id);
		List<Shijuanitem> list2 = shijuanitemDao.selectBeanList(0, 9999, " where deletestatus=0 and shiti.leixing='判断题' and shijuan.id= "+id);
		List<Shijuanitem> list3 = shijuanitemDao.selectBeanList(0, 9999, " where deletestatus=0 and shiti.leixing='填空题' and shijuan.id= "+id);
		List<Shijuanitem> list4 = shijuanitemDao.selectBeanList(0, 9999, " where deletestatus=0 and shiti.leixing='问答题' and shijuan.id= "+id);

		request.setAttribute("list1",list1 );
		request.setAttribute("list2",list2 );
		request.setAttribute("list3",list3 );
		request.setAttribute("list4",list4 );
		double  fenzhi1 = 0;
		for(Shijuanitem  si:list1){
			fenzhi1 = fenzhi1+si.getFenzhi();
		}
		double  fenzhi2 = 0;
		for(Shijuanitem  si:list2){
			fenzhi2 = fenzhi2+si.getFenzhi();
		}
		double  fenzhi3 = 0;
		for(Shijuanitem  si:list3){
			fenzhi3 = fenzhi3+si.getFenzhi();
		}
		double  fenzhi4 = 0;
		for(Shijuanitem  si:list4){
			fenzhi4 = fenzhi4+si.getFenzhi();
		}
		request.setAttribute("fenzhi1",fenzhi1 );
		request.setAttribute("fenzhi2",fenzhi2 );
		request.setAttribute("fenzhi3",fenzhi3 );
		request.setAttribute("fenzhi4",fenzhi4 );
		

		this.setUrl("shijuanitem/shijuanitemlist.jsp");
		return SUCCESS;

	}
	
	
	private ShitiDao shitiDao;

	public ShitiDao getShitiDao() {
		return shitiDao;
	}

	public void setShitiDao(ShitiDao shitiDao) {
		this.shitiDao = shitiDao;
	}
	
	

	//试卷题目列表
	public String shijuanitemlist2() {
		HttpServletRequest request = this.getRequest();
		String wenti = request.getParameter("wenti");
		String nandu = request.getParameter("nandu");
		String leixing = request.getParameter("leixing");
		String shijuanid = request.getParameter("shijuanid");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (shijuanid != null && !"".equals(shijuanid)) {

			sb.append("shijuan.id= "+shijuanid);
			sb.append(" and ");
			request.setAttribute("shijuanid", shijuanid);
		}
		
		if (wenti != null && !"".equals(wenti)) {

			sb.append(" shiti.wenti like '%" + wenti + "%'");
			sb.append(" and ");
			request.setAttribute("wenti", wenti);
		}
		
		if (nandu != null && !"".equals(nandu)) {

			sb.append(" shiti.nandu like '%" + nandu + "%'");
			sb.append(" and ");
			request.setAttribute("nandu", nandu);
		}
		
		if (leixing != null && !"".equals(leixing)) {

			sb.append(" shiti.leixing like '%" + leixing + "%'");
			sb.append(" and ");
			request.setAttribute("leixing", leixing);
		}

		
		
		
		sb.append("  deletestatus=0  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = shijuanitemDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", shijuanitemDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "shijuanitemmethod!shijuanitemlist2?shijuanid="+shijuanid, "共有" + total + "条记录"));
		request.setAttribute("url", "shijuanitemmethod!shijuanitemlist2?shijuanid="+shijuanid);
		request.setAttribute("url2", "shijuanitemmethod!shijuanitem");
		request.setAttribute("biaoti", "试卷题目管理");
		this.setUrl("shijuanitem/shijuanitemlist2.jsp");
		return SUCCESS;

	}
	
	
	
	//跳转到添加题目的页面
	public String shijuanitemadd() {
		HttpServletRequest request = this.getRequest();
		String shijuanid = request.getParameter("shijuanid");
		request.setAttribute("shijuanid",shijuanid );
		request.setAttribute("bean", shijuanDao.selectBean(" where id= "+request.getParameter("shijuanid")));
		request.setAttribute("url", "shijuanitemmethod!shijuanitemadd2?shijuanid="+shijuanid);
		request.setAttribute("biaoti", "添加题目");

		this.setUrl("shijuanitem/shijuanitemadd.jsp");
		return SUCCESS;
	}
	
	

	
	
	
	
	
	//筛选试题操作
	public String shanxuan() throws Exception{
    	HttpServletRequest request = this.getRequest();
    	
    	String leixingid = java.net.URLDecoder.decode(request.getParameter("leixingid"), "utf-8");

    	String nanduid = java.net.URLDecoder.decode(request.getParameter("nanduid"), "utf-8");
    	
    	
    	String kechengid = java.net.URLDecoder.decode(request.getParameter("kechengid"), "utf-8");
    	
    	StringBuffer sb = new StringBuffer();
    	
    	
    	sb.append(" where ");
		
		
		
		if(leixingid!=null&&!"".equals(leixingid)){
			sb.append(" leixing like '%"+leixingid+"%'");
			sb.append(" and ");
		}
		
		
		
		if(nanduid!=null&&!"".equals(nanduid)){
			sb.append(" nandu like '%"+nanduid+"%'");
			sb.append(" and ");
		}

		sb.append(" deletestatus=0  and kecheng.id="+kechengid+" order by leixing");
		String where = sb.toString();
    	
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.setContentType("text/xml");
    	response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	    response.setCharacterEncoding("UTF-8");
    	List<Shiti> list = shitiDao.selectBeanList(0, 9999, where);

        String xml = "<table border='1' ><tr><th width='120'>选择</th><th width='120'>分值</th><th width='120'>课程</th><th width='120'>难度</th>" +
        		"<th width='120'>题型</th><th width='120'>问题</th>"+
        	
        	"<th width='120'>答案</th>" +
        	"<th width='120'>选项A</th><th width='120'>选项B</th><th width='120'>选项C</th><th width='120'>选项D</th>"+
        	"</tr>";
        for(int i=0;i<list.size();i++){
        	String a ="";
        	if(list.get(i).getA()!=null)
        		a = list.get(i).getA();
        	String b ="";
        	if(list.get(i).getB()!=null)
        		b = list.get(i).getB();
        	String c ="";
        	if(list.get(i).getC()!=null)
        		c = list.get(i).getC();
        	String d ="";
        	if(list.get(i).getD()!=null)
        		d = list.get(i).getD();
        	
        	xml+="<tr>";
        	xml+="<td><input  name='ids' type='checkbox'  value='"+list.get(i).getId()+"' /></td>";
        	xml+="<td><input  name='fenzhi"+i+"' type='text'  size='3' /></td>";
        	xml+="<td>"+list.get(i).getKecheng().getMingchen()+"</td>";
        	xml+="<td>"+list.get(i).getNandu()+"</td>";
        	xml+="<td>"+list.get(i).getLeixing()+"</td>";
        	xml+="<td>"+list.get(i).getWenti()+"</td>";
        
        	xml+="<td>"+list.get(i).getDaan()+"</td>";
        	xml+="<td>"+a+"</td>";
        	xml+="<td>"+b+"</td>";
        	xml+="<td>"+c+"</td>";
        	xml+="<td>"+d+"</td>";
        	xml+="</tr>";
        	
        }
        xml+="<tr>";
        xml+="<td><input  name='a' type='hidden'  value='"+list.size()+"' /></td>";
        xml+="</tr>";
        response.getWriter().write(xml);
        return null;
    }
	
	//添加试卷题目操作
	public void shijuanitemadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		String shijuanid = request.getParameter("shijuanid");
		Shijuan shijuan = shijuanDao.selectBean(" where id= "+shijuanid);
		String[] ids = request.getParameterValues("ids");
		String a = request.getParameter("a");
		List<String> fenzhi = new ArrayList<String>();
		for(int i=0;i<Integer.parseInt(a);i++){
			String fz = request.getParameter("fenzhi"+i);
			if(fz!=null&&!"".equals(fz))
				fenzhi.add(fz);
		}
		int c1 = 0;
		int c2 = 0;
		for(int i = 0;i<ids.length;i++){
			Shijuanitem bean = shijuanitemDao.selectBean(" where deletestatus=0 and  shiti.id= "+ids[i] +" and shijuan.id= "+shijuan.getId());
			if(bean==null){
				bean = new Shijuanitem();
				bean.setShijuan(shijuan);
				bean.setFenzhi(Double.parseDouble(fenzhi.get(i)));
				bean.setShiti(shitiDao.selectBean(" where id= "+ids[i]));
		
				shijuanitemDao.insertBean(bean);
				shijuan.setZongfen(shijuan.getZongfen()+Double.parseDouble(fenzhi.get(i)));
				shijuanDao.updateBean(shijuan);
				c1++;
			}else{
				c2++;
			}
			
		}
		
	
		writer.print(
				"<script language=javascript>alert('操作成功,成功添加"+c1+"道题目，重复"+c2+"道');" +
						"window.location.href='shijuanitemmethod!shijuanitemlist2?shijuanid="+shijuanid+"';</script>");
	}
	
	//跳转到更新试题分值的页面
	public String shijuanitemupdate() {
		HttpServletRequest request = this.getRequest();
		Shijuanitem bean = shijuanitemDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("url", "shijuanitemmethod!shijuanitemupdate2?id="+bean.getId()+"&shijuanid="+request.getParameter("shijuanid"));
		request.setAttribute("biaoti", "更新试题分值");
		request.setAttribute("bean", bean);

		this.setUrl("shijuanitem/shijuanitemupdate.jsp");
		return SUCCESS;
	}
//更新试题分值的操作
	public void shijuanitemupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		String shijuanid = request.getParameter("shijuanid");
		String fenzhi = request.getParameter("fenzhi");
		Shijuanitem bean = shijuanitemDao.selectBean(" where id= "
				+ request.getParameter("id"));
		Shijuan shijuan = shijuanDao.selectBean(" where id= "+shijuanid);
		shijuan.setZongfen(shijuan.getZongfen()-bean.getFenzhi()+Double.parseDouble(fenzhi));
		shijuanDao.updateBean(shijuan);
		bean.setFenzhi(Double.parseDouble(fenzhi));
		shijuanitemDao.updateBean(bean);
		
		writer.print(
				"<script language=javascript>alert('操作成功');" +
						"window.location.href='shijuanitemmethod!shijuanitemlist2?shijuanid="+shijuanid+"';</script>");
	}
//删除试题的操作
	public void shijuanitemdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String shijuanid = request.getParameter("shijuanid");
		Shijuanitem bean = shijuanitemDao.selectBean(" where id= "
				+ request.getParameter("id"));
		Shijuan shijuan = shijuanDao.selectBean(" where id= "+shijuanid);
		shijuan.setZongfen(shijuan.getZongfen()-bean.getFenzhi());
		shijuanDao.updateBean(shijuan);
		bean.setDeletestatus(1);
		
		shijuanitemDao.updateBean(bean);
		
		
		writer.print(
				"<script language=javascript>alert('操作成功');" +
						"window.location.href='shijuanitemmethod!shijuanitemlist2?shijuanid="+shijuanid+"';</script>");
	}
	
	
}
