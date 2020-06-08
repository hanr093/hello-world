package dao;

import java.util.List;

import model.Kecheng;


public interface KechengDao  {
	
	
	
	public void insertBean(Kecheng bean);
	
	public void deleteBean(Kecheng bean);
	
	public void updateBean(Kecheng bean);

	public Kecheng selectBean(String where);
	
	public List<Kecheng> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where); 
	
	
}
