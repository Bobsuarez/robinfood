package com.robinfood.task.infrastructure.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
  "com.robinfood.task.domain",
  "com.robinfood.task.infrastructure",
  "com.robinfood.task.modules"})
@EntityScan(basePackages = {"com.robinfood.task.domain.persistence.entity"})
@EnableJpaRepositories(basePackages = {"com.robinfood.task.domain.persistence.repository"})
public class TaskApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(TaskApplication.class, args);
  }

}
