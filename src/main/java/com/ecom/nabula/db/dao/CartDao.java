package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Cart;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class CartDao extends AbstractDAO<Cart> {

    public CartDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

//    public void findById(long )
//    public List<Cart> findByCustomerId(long customerId) {
////        return list(namedQuery("Cart.findByCustomerId")
////                .setParameter("customerId", customerId));
////        return List<new Cart();
//    }

    public void update(Cart cart) {
        currentSession().update(cart);
    }



}
