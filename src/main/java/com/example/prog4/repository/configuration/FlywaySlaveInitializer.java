package com.example.prog4.repository.configuration;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywaySlaveInitializer {
    private final DataSource employeeDataSource;
    private final DataSource employeeCnapsDataSource;

    public FlywaySlaveInitializer(
                                  DataSource employeeDataSource,
                                  @Qualifier("employeeCnapsDataSource")
                                  DataSource employeeCnapsDataSource) {
        this.employeeDataSource = employeeDataSource;
        this.employeeCnapsDataSource = employeeCnapsDataSource;
    }

    @PostConstruct
    public void migrate() {
        migrateDataSource(employeeDataSource, "classpath:/db/migration/default");
        migrateDataSource(employeeCnapsDataSource, "classpath:/db/migration/cnaps");
    }

    private void migrateDataSource(DataSource dataSource, String... locations) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(locations).load();
        flyway.migrate();
    }

}
