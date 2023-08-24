package com.example.prog4.repository.employeeCnaps.configuration;

import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

import static com.example.prog4.repository.configuration.LocalContainerEntityManagerFactoryBeanCreator.createEntityManagerFactoryBean;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(basePackages = "com.example.prog4.repository.employeeCnaps",
        entityManagerFactoryRef = "employeeCnapsEntityManager",
        transactionManagerRef = "employeeCnapsTransactionManager")
@AllArgsConstructor
public class PersistenceEmployeeCnapsAutoConfiguration {
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean employeeCnapsEntityManager() {
        final HashMap<String, Object> prop = new HashMap<>();
        prop.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        prop.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        return createEntityManagerFactoryBean(EmployeeCnapsDataSource(),
                "com.example.prog4.repository.employeeCnaps", prop);
    }

    @Bean(name = "employeeCnapsDataSource")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource EmployeeCnapsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager EmployeeCnapsTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(employeeCnapsEntityManager().getObject());
        return transactionManager;
    }
}
