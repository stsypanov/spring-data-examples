package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.data.IdAndNameDto;
import com.luxoft.logeek.entity.SimpleEntity;
import com.luxoft.logeek.repository.SimpleEntityRepository;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class ProjectionVsDtoBenchmark extends BenchmarkBase {
  private SimpleEntityRepository repository;

  @Param({"1", "100"})
  private int count;

  @Setup
  public void before() {
    super.initContext();
    repository = context.getBean(SimpleEntityRepository.class);
    IntStream.range(0, count)
      .boxed()
      .map(id -> new SimpleEntity(id, "ivan"))
      .forEach(repository::save);
  }

  @TearDown
  public void after() {
    repository.deleteAllInBatch();
  }

  @Benchmark
  public List<IdAndNameDto> dto() {
    return repository.findAllByNameUsingDto("ivan");
  }

  @Benchmark
  public List<HasIdAndName> projection() {
    return repository.findAllByName("ivan");
  }
}
