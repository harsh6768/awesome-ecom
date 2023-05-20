package com.ecom.nabula;

import com.ecom.nabula.db.entities.Customer;
import com.ecom.nabula.db.entities.Order;
import com.ecom.nabula.db.entities.Product;
import com.ecom.nabula.validator.CustomValidationExceptionMapper;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class EcomApplication extends Application<EcomConfiguration> {

    private  HibernateBundle<EcomConfiguration> hibernateBundle;

    private GuiceBundle guiceBundle;

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

        // Load the appropriate configuration file based on the environment
//        String environmentName = System.getenv("APP_ENV");
//        String configFile = String.format("%s.yml", environmentName);

//        String bootstrap = ecomConfiguration.getBootstrapValue();

        // Use the ConfigurationSourceProvider to load the configuration file
//        bootstrap.setConfigurationSourceProvider(new FileConfigurationSourceProvider());
//        configuration = bootstrap.getConfigurationFactoryFactory()
//                .create(MyConfiguration.class, bootstrap.getValidatorFactory().getValidator(), bootstrap.getObjectMapper(), "dw")
//                .build(bootstrap.getConfigurationSourceProvider().open(configFile));

        System.out.println("class name---->"+getClass().getPackage().getName());

        System.out.println("Server is up and running!");
        // registering custom exeption to handle input validation
        environment.jersey().register(new CustomValidationExceptionMapper());

    }

    @Override
    public void initialize(Bootstrap<EcomConfiguration> bootstrap) {
        super.initialize(bootstrap);
        loadHibernateBundle(bootstrap);
        loadGuiceBundle(bootstrap);
    }

    private void loadGuiceBundle(Bootstrap<EcomConfiguration> bootstrap){

        guiceBundle= new GuiceBundle.Builder().enableAutoConfig(getClass().getPackage().getName())
                .modules(
                        new EcomApplicationModule(
                                hibernateBundle
                        )
                )
                .build();
        bootstrap.addBundle(guiceBundle);

    }

}
