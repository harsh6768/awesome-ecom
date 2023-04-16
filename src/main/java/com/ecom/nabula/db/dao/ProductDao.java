package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Product;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class ProductDao extends AbstractDAO<Product> {

    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void insert(Product product){
        try{
           persist(product);
        }catch(Exception error){
            System.out.println("Add-New_Product_Error::::->"+error);
        }
    }

    public List<Product> findAll(){
        try{
            Query<Product> query =currentSession().createQuery("from Product",Product.class);
            return list(query);
        }catch(Exception error){
            System.out.println("product-findall-method::::->"+error);
            throw  error;
        }
    }

    public Product findOne(long id){
        try{
           return get(id);
        }catch (Exception error){
            System.out.println("find-one-error:::->>>"+error);
            throw error;
        }
    }



}
