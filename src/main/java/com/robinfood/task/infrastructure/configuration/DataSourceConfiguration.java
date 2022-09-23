/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.infrastructure.configuration;

import com.robinfood.task.domain.utils.FileUtil;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Bobsuarez
 */
@Configuration
@Slf4j
public class DataSourceConfiguration
{

  @Bean
  public DataSource getDataSource()
  {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.postgresql.Driver");
    dataSourceBuilder.url("jdbc:postgresql://localhost:5433/task");
    dataSourceBuilder.username("postgres");
    dataSourceBuilder.password("postgres");
    return dataSourceBuilder.build();
  }

  @Bean
  public void createFolder()
  {
    var path = FileUtil.createFolder("tareas");
    log.info("Carpeta hecha ", path);
  }
}
