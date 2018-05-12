package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BenchmarkBase {
	protected ThreadLocalRandom random;
	protected ConfigurableApplicationContext context;

	void initContext() {
		context = SpringApplication.run(AppConfig.class);
		random = ThreadLocalRandom.current();
	}
}