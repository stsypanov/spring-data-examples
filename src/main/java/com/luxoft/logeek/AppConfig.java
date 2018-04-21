package com.luxoft.logeek;

import com.luxoft.logeek.repository.BaseJpaRepositoryImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class)
public class AppConfig {
}
