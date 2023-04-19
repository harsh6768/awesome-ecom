package com.ecom.nabula.db.dao;

import com.ecom.nabula.db.entities.Customer;
import com.ecom.nabula.db.entities.Order;
import com.ecom.nabula.db.entities.Product;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao extends AbstractDAO<Order> {

    final ProductDao productDao;
    final CustomerDao customerDao;
    public OrderDao(SessionFactory sessionFactory,CustomerDao customerDao,ProductDao productDao) {
        super(sessionFactory);
        this.customerDao=customerDao;
        this.productDao=productDao;
    }

    public void insert(Order orderData) throws Exception {
        try{
            double totalPrice = 0;
            //Giving Error :  Create Order :::->java.util.ConcurrentModificationException
            // for (Product tempProduct : productList) {
            //  Product product = currentSession().get(Product.class, tempProduct.getId());
            //  orderData.getProducts().add(product);
            // }

            Optional<Customer> customer=customerDao.findById(orderData.getCustomer().getId());
            if(customer.isEmpty()){
                // register signup
                throw new Exception("Customer does not exist,Signup First!");
            }
            if(orderData.getProducts().size()==0){
                throw new Exception("Cart is Empty!");
            }
            List<Product> productsToAdd=new ArrayList<>();
            for(Product p: orderData.getProducts()) {
                Product product = productDao.findOne(p.getId());
                if(product != null) {
                    productsToAdd.add(product);
                    totalPrice += product.getPrice();
                }
            }
            //setting products
            orderData.setProducts(productsToAdd);
            orderData.setTotalPrice(totalPrice);

            currentSession().persist(orderData);

        }catch (Exception error){
            System.out.println("Create Order :::->"+error);
            throw new Exception(error);
        }
    }

    public List<Order> findAll() throws Exception {
        try{
            Query<Order> query = currentSession().createQuery("from Order", Order.class);
            return list(query);
        }catch (Exception error){
            throw  new Exception(error);
        }
    }
    public Order findOne(long orderId){
        try{
           return get(orderId);
        }catch (Exception error){
            System.out.println("get-order-by-id:::->"+orderId);
            throw error;
        }
    }
}
