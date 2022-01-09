package jmaster.dao;

import jmaster.entity.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface EmailDao {
    void add(Message message);
}

@Repository
@Transactional
class EmailDaoImpl implements EmailDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Message message) {
        entityManager.persist(message);
    }
}
