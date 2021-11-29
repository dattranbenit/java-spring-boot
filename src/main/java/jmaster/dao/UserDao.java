package jmaster.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jmaster.entity.User;

public interface UserDao {
	void insert(User user);

	void update(User user);

	void delete(User user);

	User get(Long id);

	User getByUserName(String userName);

	List<User> search(String findName, int start, int length);

	List<User> getAll();
}

@Repository
@Transactional /// quan ly giao dich.
//dam bao tat ca cac ham deu thanh cong hoac faile
class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insert(User user) {
		entityManager.persist(user);
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void delete(User user) {
	}

	@Override
	public User get(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User getByUserName(String userName) {
		String jql = " select u from User u where u.username = :name ";
		return entityManager.createQuery(jql, User.class).setParameter("name", userName).getSingleResult();
	}

	@Override
	public List<User> search(String findName, int start, int length) {
		String jql = "select u from User u where name like :name";
		Query query = entityManager.createQuery(jql, User.class);
		query.setParameter("name", "%" + findName + "%");
		query.setFirstResult(start).setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public List<User> getAll() {
		String jql = "select u from User u ";
		Query query = entityManager.createQuery(jql, User.class);
		return query.getResultList();

	}

}