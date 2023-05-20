package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Customer;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;


public class CustomerDao extends AbstractDAO<Customer> {

    @Inject
    public CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Customer> findById(Long id) {
        try{
            return Optional.ofNullable(get(id));
        }catch (Exception error){
            throw  error;
        }
    }

    public Customer create(Customer customer) {
        try{
            System.out.println("Custerom"+customer.getEmail());
            persist(customer);
            return customer;
        } catch(Exception e){
            System.out.println("Error:"+e);
        }
        return customer;
    }

    public List<Customer> findAll() {

       return list((Criteria) namedQuery("Customer.findAll"));
//      return list(namedQuery("Customer.findAll"));
    }

    public Optional<Customer> findByEmail(String email) {
//        String queryString= namedQuery("Customer.findByEmail").setParameter("email",email).getQueryString();
//        System.out.println("Query--->"+queryString);
        return Optional.ofNullable(uniqueResult(namedTypedQuery("Customer.findByEmail")
                .setParameter("email", email)));
    }
}
