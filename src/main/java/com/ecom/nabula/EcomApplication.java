package com.ecom.nabula;

import com.ecom.nabula.db.dao.CustomerDao;
import com.ecom.nabula.db.dao.OrderDao;
import com.ecom.nabula.db.dao.ProductDao;
import com.ecom.nabula.db.entities.Customer;
import com.ecom.nabula.db.entities.Order;
import com.ecom.nabula.db.entities.Product;
import com.ecom.nabula.resources.CustomerResource;
import com.ecom.nabula.resources.OrderResource;
import com.ecom.nabula.resources.ProductResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;

public class EcomApplication extends Application<EcomConfiguration> {

    private  HibernateBundle<EcomConfiguration> hibernateBundle;
    private  void loadHibernateBundle(Bootstrap<EcomConfiguration> bootstrap){

       hibernateBundle = new HibernateBundle<EcomConfiguration>(Customer.class, Product.class, Order.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(EcomConfiguration ecomConfiguration) {
                return ecomConfiguration.getDatabase();
            }
       };
       bootstrap.addBundle(hibernateBundle);
    }


    public static void main(String[] args) throws Exception {
        new EcomApplication().run(args);
    }
    @Override
    public void run(EcomConfiguration ecomConfiguration, Environment environment) throws Exception {
        System.out.println("Server is up and running!");
        final SessionFactory sessionFactory=hibernateBundle.getSessionFactory();
        // Register User Resource
        final CustomerDao customerDao = new CustomerDao(sessionFactory);
        environment.jersey().register(new CustomerResource(customerDao));

        final ProductDao productDao=new ProductDao(sessionFactory);
        environment.jersey().register(new ProductResource(productDao));

        final OrderDao orderDao=new OrderDao(sessionFactory,customerDao,productDao);
        environment.jersey().register(new OrderResource(orderDao));
    }

    @Override
    public void initialize(Bootstrap<EcomConfiguration> bootstrap) {
//        super.initialize(bootstrap);
        loadHibernateBundle(bootstrap);
    }

}
