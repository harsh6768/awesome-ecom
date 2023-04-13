package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Product;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class ProductDao extends AbstractDAO<Product> {

    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Product create(Product product) {
        return persist(product);
    }

    public List<Product> findAll() {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        return list(criteriaQuery);
    }

    public void deleteById(Long id) {
        namedQuery("Product.deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
