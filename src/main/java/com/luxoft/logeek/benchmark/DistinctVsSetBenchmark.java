package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.PupilRepository;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class DistinctVsSetBenchmark extends BenchmarkBase {
  private PupilRepository repository;

  @Param({"10", "100", "1000", "10000"})
  int count;

  @Setup()
  public void init() {
    super.initContext();
    repository = context.getBean(PupilRepository.class);
    List<Pupil> pupils = random
      .ints(count, 0, count * 2)
      .boxed()
      .map(randomLong -> new Pupil(randomLong, String.valueOf(random.nextInt(100))))
      .collect(Collectors.toList());

    repository.saveAll(pupils);
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
