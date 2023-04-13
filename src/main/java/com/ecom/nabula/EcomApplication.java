package com.ecom.nabula;

import com.ecom.nabula.db.dao.CustomerDao;
import com.ecom.nabula.db.dao.ProductDao;
import com.ecom.nabula.db.entities.Customer;
import com.ecom.nabula.db.entities.Product;
import com.ecom.nabula.resources.CustomerResource;
import com.ecom.nabula.resources.ProductResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class EcomApplication extends Application<EcomConfiguration> {

    private  HibernateBundle<EcomConfiguration> hibernateBundle;
    private  void loadHibernateBundle(Bootstrap<EcomConfiguration> bootstrap){

       hibernateBundle = new HibernateBundle<EcomConfiguration>(Customer.class, Product.class) {
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
        // Register User Resource
        final CustomerDao customerDAO = new CustomerDao(hibernateBundle.getSessionFactory());
        final CustomerResource customerResource = new CustomerResource(customerDAO);
        environment.jersey().register(customerResource);

        final ProductDao productDao=new ProductDao(hibernateBundle.getSessionFactory());
        final ProductResource productResource=new ProductResource(productDao);
        environment.jersey().register(productResource);



    }

    @Override
    public void initialize(Bootstrap<EcomConfiguration> bootstrap) {
//        super.initialize(bootstrap);
        loadHibernateBundle(bootstrap);
    }


}
