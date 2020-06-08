package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Kecheng;

import org.hibernate.HibernateException; 
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.KechengDao;









public class KechengDaoImpl extends HibernateDaoSupport implements  KechengDao{


	public void deleteBean(Kecheng bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Kecheng bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Kecheng selectBean(String where) {
		List<Kecheng> list = this.getHibernateTemplate().find("from Kecheng " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Kecheng "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Kecheng> selectBeanList(final int start,final int limit,final String where) {
		return (List<Kecheng>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Kecheng> list = session.createQuery("from Kecheng "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Kecheng bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
