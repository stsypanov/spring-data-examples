package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.entity.EntityWithManyFields;
import com.luxoft.logeek.repository.EntityWithManyFieldsRepository;
import org.openjdk.jmh.annotations.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(value = Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class ProjectionVsDataBenchmark extends BenchmarkBase {
	private EntityWithManyFieldsRepository repository;

	@Setup
	public void init() {
		super.initContext();
		repository = context.getBean(EntityWithManyFieldsRepository.class);
		List<EntityWithManyFields> entities = random
				.longs(5000, 0, 400)
				.boxed()
				.map(randomLong -> new EntityWithManyFields(randomLong, String.valueOf(randomLong), "ivan"))
				.collect(Collectors.toList());

		repository.saveAll(entities);
	}

	@Benchmark
	public Collection<HasIdAndName> findAllByNameUsingObject() {
		return repository.findAllByNameUsingObject("ivan");
	}

	@Benchmark
	public Collection<HasIdAndName> findAllByName() {
		return repository.findAllByName("ivan");
	}
}
