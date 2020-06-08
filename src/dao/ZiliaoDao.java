package dao;

import java.util.List;

import model.Ziliao;


public interface ZiliaoDao  { 
	
	
	
	public void insertBean(Ziliao bean);
	
	public void deleteBean(Ziliao bean);
	
	public void updateBean(Ziliao bean);

	public Ziliao selectBean(String where);
	
	public List<Ziliao> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
