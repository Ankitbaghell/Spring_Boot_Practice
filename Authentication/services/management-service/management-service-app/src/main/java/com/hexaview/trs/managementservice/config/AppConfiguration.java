package com.hexaview.trs.managementservice.config;

import static com.hexaview.trs.constants.CommonConstants.MONGO_DB;
import static com.hexaview.trs.constants.CommonConstants.POSTGRES_DB;

import com.hexaview.trs.common.database.connection.Datasource;
import com.hexaview.trs.managementservice.adapter.persistence.ManagementRdbmsRepository;
import com.hexaview.trs.managementservice.model.DataSourceConfig;
import com.hexaview.trs.managementservice.port.persistence.ManagementRepository;
import com.hexaview.trs.managementservice.service.IManagementService;
import com.hexaview.trs.managementservice.service.impl.ManagementServiceImpl;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Autowired IManagementService managementService;

  Map<Object, Object> mapOfTenantIdAndDatasource = new HashMap<>();

  @Bean
  public Datasource datasource(
      @Value("${spring.datasource.url}") String jdbcUrl,
      @Value("${spring.datasource.username}") String username,
      @Value("${spring.datasource.password}") String password) {
    return Datasource.getInstance(jdbcUrl, username, password);
  }

  @Bean
  public ManagementRepository managementRepository(Datasource datasource) {
    return new ManagementRdbmsRepository(datasource);
  }

  @Bean
  public IManagementService managementService(ManagementRepository managementRepository) {
    return new ManagementServiceImpl(managementRepository);
  }

  /** + used to get all tenant data source from database */
  @PostConstruct
  public void getAll() {
    Flyway flywayIntegration = Flyway.configure().dataSource(url, username, password).load();

    flywayIntegration.migrate();

    Map<String, DataSourceConfig> mapOfTenantIdAndPostgresConfig =
        managementService.getAll(POSTGRES_DB);
    Map<String, DataSourceConfig> mapOfTenantIdAndMongoConfig = managementService.getAll(MONGO_DB);
  }
}
