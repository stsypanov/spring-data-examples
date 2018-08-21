package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.data.IdAndNameDto;
import com.luxoft.logeek.entity.EntityWithManyFields;
import com.luxoft.logeek.repository.EntityWithManyFieldsRepository;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
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
public class ProjectionVsDataBenchmark extends BenchmarkBase {
    private EntityWithManyFieldsRepository repository;

    @Setup
    public void before() {
        super.initContext();
        repository = context.getBean(EntityWithManyFieldsRepository.class);
        IntStream.range(0, 10)
                .boxed()
                .map(id -> new EntityWithManyFields(id, "ivan"))
                .forEach(repository::save);
    }

    @TearDown
    public void after() {
        repository.deleteAllInBatch();
    }

    @Benchmark
    public List<IdAndNameDto> findAllByNameUsingObject() {
        return repository.findAllByNameUsingDto("ivan");
    }

    @Benchmark
    public List<HasIdAndName> findAllByName() {
        return repository.findAllByName("ivan");
    }
}
