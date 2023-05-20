package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Product;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class ProductDao extends AbstractDAO<Product> {

    @Inject
    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void insert(Product product){
        try{
//            product.setId(1);
            System.out.println("Product-id--->:"+product.getId());
            System.out.println("Product-desc--->:"+product.getDescription());
            System.out.println("Product-title--->:"+product.getTitle());
            System.out.println("Product-price--->:"+product.getPrice());
            currentSession().persist(product);
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
