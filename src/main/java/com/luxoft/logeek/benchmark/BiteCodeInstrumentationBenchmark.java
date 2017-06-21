package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.EntityWithManyStringFields;
import com.luxoft.logeek.service.ExecutionService;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(value = {Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class BiteCodeInstrumentationBenchmark extends BenchmarkBase {
	private ExecutionService service;

	@Param({"10","100", "1000", "10000"})
	private int size;

	@Setup
	public void initBase() {
		super.initContext();
		service = context.getBean(ExecutionService.class);
	}

	@Setup(Level.Iteration)
	public void init() {
		service.setUp(size);
	}

	@TearDown(Level.Iteration)
	public void tearDown() {
		service.tearDown();
	}

	@Benchmark
	public List<EntityWithManyStringFields> measureDirtyChecking() {
		return service.execute();
	}
}
