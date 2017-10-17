package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeRepository;
import com.luxoft.logeek.service.FindOneService;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class DirtyCheckingBenchmark extends BenchmarkBase {
    private FindOneService service;
    private Long id;

    @Setup
    public void setup() {
        super.initContext();
        service = context.getBean(FindOneService.class);
        id = service.save(new SomeEntity(1L, 2L)).getId();
    }

    @Benchmark
    public SomeEntity measureFindOneReadOnly() {
        return service.findOne(id, true);
    }

    @Benchmark
    public SomeEntity measureFindOne() {
        return service.findOne(id, false);
    }

    @Benchmark
    public SomeEntity measureConventionalFindOne() {
        return service.findOne(id);
    }
}
