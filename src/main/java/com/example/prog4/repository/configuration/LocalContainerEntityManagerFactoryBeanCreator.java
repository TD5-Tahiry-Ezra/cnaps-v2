package com.example.prog4.repository.configuration;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

public class LocalContainerEntityManagerFactoryBeanCreator {
    private LocalContainerEntityManagerFactoryBeanCreator() {
    }
    public static LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean(
            DataSource dataSource, String packagesToScan, HashMap<String, Object> properties) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(packagesToScan);

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(properties);
        return em;
    }
}
