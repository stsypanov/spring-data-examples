package com.luxoft.logeek;

import com.luxoft.logeek.repository.BaseJpaRepositoryImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@EnableAutoConfiguration
@EntityScan(basePackages = "com.luxoft.logeek.entity")
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class)
public class AppConfig {
}
