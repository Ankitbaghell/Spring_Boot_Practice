package com.flyway.demo.flywaydemo.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FlywaySlaveInitializer {

    @Value("${spring.datasource.url}")
    String firstDatasourceUrl;
    @Value("${spring.datasource.username}")
    String firstDatasourceUser;
    @Value("${spring.datasource.password}")
    String firstDatasourcePassword;

    @Value("${spring.datasource.url2}")
    String secondDatasourceUrl;
    @Value("${spring.datasource.username}")
    String secondDatasourceUser;
    @Value("${spring.datasource.password}")
    String secondDatasourcePassword;


//    @PostConstruct
//    public void migrateFlyway(String url, String user, String password, String flywayMigrationPath) {
//        String base_location = "filesystem:./src/main/resources/";
//        Flyway flywayIntegration = Flyway.configure()
//                .dataSource(url, user, password)
//                .locations(base_location + flywayMigrationPath)
//                .load();
//
//        flywayIntegration.migrate();
//
//    }

    public boolean migrateFlyway(String url, String user, String password, String flywayMigrationPath) {
        String base_location = "filesystem:./src/main/resources/";
        Flyway flywayIntegration = Flyway.configure()
                .dataSource(url, user, password)
                .locations(base_location + flywayMigrationPath)
                .load();

        MigrateResult migrate = flywayIntegration.migrate();
        return migrate.success;
    }


//    @PostConstruct
//    public void migrateFlyway() {
//        Flyway flyway1 = Flyway.configure()
//                .dataSource(firstDatasourceUrl, firstDatasourceUser, firstDatasourcePassword)
//                .locations("filesystem:./src/main/resources/db/migration")
//                .load();
//
//        Flyway flyway2 = Flyway.configure()
//                .dataSource(secondDatasourceUrl, secondDatasourceUser, secondDatasourcePassword)
//                .locations("filesystem:./src/main/resources/db.migration2")
//                .load();
//
//        flyway1.migrate();
//        flyway2.migrate();
//    }

}
