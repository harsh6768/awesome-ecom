package com.ecom.nabula;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

public class EcomApplicationModule extends AbstractModule {

    private final HibernateBundle<EcomConfiguration> hibernateBundle;

    public EcomApplicationModule(HibernateBundle<EcomConfiguration> hibernateBundle) {
        this.hibernateBundle=hibernateBundle;
    }

    @Override
    protected void configure() {
        super.configure();
    }

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory(){
        return hibernateBundle.getSessionFactory();
    }
}
