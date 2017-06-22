package com.luxoft.logeek.benchmark.runner;

import com.luxoft.logeek.benchmark.ByteCodeInstrumentationBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
//				.include(DistinctVsSetBenchmark.class.getSimpleName())
//				.include(ProjectionVsDataBenchmark.class.getSimpleName())
				.include(ByteCodeInstrumentationBenchmark.class.getSimpleName())
//				.include(ByteCodeInstrumentationBenchmarkForSingleEntity.class.getSimpleName())
				.warmupIterations(10)
//				.verbosity(VerboseMode.EXTRA)
				.measurementIterations(10)
//				.addProfiler(GCProfiler.class)
//				.addProfiler(HotspotMemoryProfiler.class)
//				.addProfiler(HotspotAllocationProfiler.class)
//				.addProfiler(MemoryProfiler.class)
//				.addProfiler(HotspotRuntimeProfiler.class)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}