package dao;

import java.util.List;

import model.Exam;


public interface ExamDao  {
	
	
	
	public void insertBean(Exam bean); 
	
	public void deleteBean(Exam bean);
	
	public void updateBean(Exam bean);

	public Exam selectBean(String where);
	
	public List<Exam> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
