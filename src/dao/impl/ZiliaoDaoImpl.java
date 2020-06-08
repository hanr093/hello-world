package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Ziliao;

import org.hibernate.HibernateException; 
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ZiliaoDao;









public class ZiliaoDaoImpl extends HibernateDaoSupport implements  ZiliaoDao{


	public void deleteBean(Ziliao bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Ziliao bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Ziliao selectBean(String where) {
		List<Ziliao> list = this.getHibernateTemplate().find("from Ziliao " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Ziliao "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Ziliao> selectBeanList(final int start,final int limit,final String where) {
		return (List<Ziliao>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Ziliao> list = session.createQuery("from Ziliao "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Ziliao bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
