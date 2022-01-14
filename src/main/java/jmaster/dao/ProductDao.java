package jmaster.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jmaster.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface ProductDao {
    void insert(Product product);

    Product get(Long id);
    
    List<Product> getAll();
}

@Repository
@Transactional
class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product get(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        String jql = "select u from Product u ";
        Query query = entityManager.createQuery(jql, Product.class);
        return query.getResultList();
    }
}