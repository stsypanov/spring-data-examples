package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.PupilRepository;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class DistinctVsSetBenchmark extends BenchmarkBase {
	private PupilRepository repository;

	@Setup()
	public void init() {
		super.initContext();
		repository = context.getBean(PupilRepository.class);
		List<Pupil> pupils = random
				.ints(5000, 0, 400)
				.boxed()
				.map(randomLong -> new Pupil(randomLong, String.valueOf(random.nextInt(100))))
				.collect(Collectors.toList());

		repository.save(pupils);
	}

	@Benchmark
	public List<String> measureDistinct() {
		return repository.findAllNames();
	}

	@Benchmark
	public Set<String> measureSet() {
		return repository.findAllNamesAsSet();
	}
}
