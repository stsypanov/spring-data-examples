package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public abstract class BenchmarkBase {
	protected Random random;
	protected AnnotationConfigApplicationContext context;

	protected void initContext() {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		random = new Random();
	}
}