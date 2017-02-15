package com.luxoft.logeek;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JpaConfig.class)
@ComponentScan(basePackages = {"com.luxoft.logeek"})
public class AppConfig {
}
