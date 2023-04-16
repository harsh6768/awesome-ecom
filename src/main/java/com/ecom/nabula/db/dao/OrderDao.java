package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Order;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class OrderDao extends AbstractDAO<Order> {

    public OrderDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
