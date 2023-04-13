package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Order;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDao extends AbstractDAO<Order> {

    public OrderDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Order create(Order order) {
        return persist(order);
    }

    public List<Order> findAll() {
        Query<Order> query = currentSession().createQuery("from Order", Order.class);
        return list(query);
    }

    public Order findById(long id) {
        return get(id);
    }

    public Order update(Order order) {
        return persist(order);
    }

    public void delete(Order order) {
        currentSession().delete(order);
    }
}
