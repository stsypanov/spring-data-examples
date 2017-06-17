package com.luxoft.logeek.benchmark.runner;

import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.service.ExecutionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
	protected static Random random;
	protected static AnnotationConfigApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		random = new Random(System.nanoTime());
		ExecutionService bean = context.getBean(ExecutionService.class);
		bean.setUp();
		bean.execute();

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


//		try {
//			Thread.sleep(2*60*1000);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
	}
}
