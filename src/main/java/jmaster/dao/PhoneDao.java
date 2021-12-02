package jmaster.dao;

import jmaster.entity.Phone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public interface PhoneDao {
    void insert(Phone phone);

    void update(Phone phone);

    void delete(Phone phone);

    Phone get(Long id);

    List<Phone> getByUserId(Long id);

    List<Phone> search(String findName, int start, int length);

    List<Phone> getAll();
}


@Repository
@Transactional /// quan ly giao dich.
//dam bao tat ca cac ham deu thanh cong hoac faile
class PhoneDaoImpl implements PhoneDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Phone phone) {
        entityManager.persist(phone);
    }

    @Override
    public void update(Phone phone) {
        entityManager.merge(phone);
    }

    @Override
    public void delete(Phone phone) {
    }

    @Override
    public Phone get(Long id) {
        return entityManager.find(Phone.class, id);
    }

    @Override
    public List<Phone> getByUserId(Long id) {
        String jql = " select u from Phone u where u.user_id = :id ";
        return entityManager.createQuery(jql, Phone.class).setParameter("user_id", id).getResultList();
    }

    @Override
    public List<Phone> search(String findName, int start, int length) {
        String jql = "select u from Phone u where user_id like :name";
        Query query = entityManager.createQuery(jql, Phone.class);
        query.setParameter("name", "%" + findName + "%");
        query.setFirstResult(start).setMaxResults(length);
        return query.getResultList();
    }

    @Override
    public List<Phone> getAll() {
        String jql = "select u from Phone u ";
        Query query = entityManager.createQuery(jql, Phone.class);
        return query.getResultList();
    }

}

