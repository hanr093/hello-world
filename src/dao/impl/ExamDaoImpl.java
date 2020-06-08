package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Exam;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ExamDao;









public class ExamDaoImpl extends HibernateDaoSupport implements  ExamDao{


	public void deleteBean(Exam bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Exam bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Exam selectBean(String where) {
		List<Exam> list = this.getHibernateTemplate().find("from Exam " +where);
		if(list.size()==0){
			return null; 
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Exam "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Exam> selectBeanList(final int start,final int limit,final String where) {
		return (List<Exam>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Exam> list = session.createQuery("from Exam "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Exam bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
