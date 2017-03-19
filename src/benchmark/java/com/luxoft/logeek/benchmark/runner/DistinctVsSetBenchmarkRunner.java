package com.luxoft.logeek.benchmark.runner;

import com.luxoft.logeek.benchmark.DistinctVsSetBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class DistinctVsSetBenchmarkRunner {
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(DistinctVsSetBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(10)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}