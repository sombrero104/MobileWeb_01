package sample.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import sample.dao.GoodsDao;
import sample.domain.Goods;

public class GoodsDaoImpl implements GoodsDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Goods> getGoodsList(int startNum, int endNum) {
		EntityManager entityManager = sessionFactory.createEntityManager();
		TypedQuery<Goods> typedQuery = entityManager.createQuery("from Goods", Goods.class);
		typedQuery.setFirstResult(startNum);
		typedQuery.setMaxResults(endNum);
		return typedQuery.getResultList();
	}
	
}
