package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Cart;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class CartDao extends AbstractDAO<Cart> {

    @Inject
    public CartDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void create(Cart cart){
        try{
            currentSession().persist(cart);
        }catch (Exception error){

        }
    }
}
