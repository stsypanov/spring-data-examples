package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public abstract class BenchmarkBase {
	protected Random random;
	protected ConfigurableApplicationContext context;

	protected void initContext() {
		context = SpringApplication.run(AppConfig.class);
		random = new Random();
	}
}