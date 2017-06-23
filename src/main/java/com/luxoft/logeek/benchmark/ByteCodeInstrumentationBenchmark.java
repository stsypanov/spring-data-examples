package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.EntityWithManyStringFields;
import com.luxoft.logeek.service.ExecutionService;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(value = {Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class ByteCodeInstrumentationBenchmark extends BenchmarkBase {
	private ExecutionService service;

	@Param({"10", "100", "1000", "10000"})
	private int entityCount;

	@Setup
	public void initBase() {
		super.initContext();
		service = context.getBean(ExecutionService.class);
	}

	@Setup(Level.Invocation)
	public void init() {
		service.setUp(entityCount);
	}

	@TearDown(Level.Invocation)
	public void tearDown() {
		service.tearDown();
	}

	@Benchmark
	public List<EntityWithManyStringFields> measureDirtyCheckingForModifiedFields() {
		return service.executeFieldModification();
	}

	@Benchmark
	public List<EntityWithManyStringFields> measureDirtyCheckingForNonModifiedFields() {
		return service.executeWithoutFieldModification();
	}
}
