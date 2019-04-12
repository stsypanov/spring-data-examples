package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.EntityWithManyStringFields;
import com.luxoft.logeek.repository.EntityWithManyStringFieldsRepository;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

@BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime, Mode.SingleShotTime})
//@BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime})
//@BenchmarkMode(value = {Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class ByteCodeInstrumentationBenchmark extends BenchmarkBase {
	private EntityWithManyStringFieldsRepository repository;

	@Setup
	public void init() {
		super.initContext();
		repository = context.getBean(EntityWithManyStringFieldsRepository.class);
		List<EntityWithManyStringFields> pupils = random
				.ints(100000, 0, 400)
				.boxed()
				.map(rnd -> {
					EntityWithManyStringFields entity = new EntityWithManyStringFields();
					entity.setField1(random.nextGaussian() +"");
					entity.setField2(random.nextGaussian() +"");
					entity.setField3(random.nextGaussian() +"");
					entity.setField4(random.nextGaussian() +"");
					entity.setField5(random.nextGaussian() +"");
					entity.setField6(random.nextGaussian() +"");
					entity.setField7(random.nextGaussian() +"");
					entity.setField8(random.nextGaussian() +"");
					return entity;
				})
				.collect(toList());

		repository.saveAll(pupils);
	}

	@Benchmark
	public List<EntityWithManyStringFields> measureConventionalDirtyChecking() {
		List<EntityWithManyStringFields> entities = repository.findAll();
		entities = entities.stream()
				.peek(entity -> {
					entity.setField1(random.nextGaussian() +"");
//					entity.setField2(random.nextGaussian() +"");
//					entity.setField3(random.nextGaussian() +"");
//					entity.setField4(random.nextGaussian() +"");
//					entity.setField5(random.nextGaussian() +"");
//					entity.setField6(random.nextGaussian() +"");
//					entity.setField7(random.nextGaussian() +"");
//					entity.setField8(random.nextGaussian() +"");
				})
				.collect(toList());

		entities = repository.saveAll(entities);
		repository.flush();
		return entities;
	}
}
