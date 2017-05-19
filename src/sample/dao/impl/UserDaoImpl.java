package sample.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.SessionFactory;
import sample.dao.UserDao;
import sample.domain.User;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<User> getUserList() {
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		return sessionFactory.getCurrentSession().createQuery(criteria).list();
	}
	
	public User findUserByNo(long no) {
		return sessionFactory.getCurrentSession().get(User.class, no);
	}
	
	public User findUserById(String username) {
		return sessionFactory.getCurrentSession().createQuery("select u from User u left join u.authorities ua where u.username=:username", User.class)
				.setParameter("username", username).getSingleResult();
	}
	
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}
	
	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}
	
}
