package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.EntityWithManyStringFields;
import com.luxoft.logeek.repository.EntityWithManyStringFieldsRepository;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

//@BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime, Mode.SingleShotTime})
//@BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime})
@BenchmarkMode(value = {Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ByteCodeInstrumentationBenchmarkForSingleEntity extends BenchmarkBase {
    private EntityWithManyStringFieldsRepository repository;
    private EntityWithManyStringFields entity;

    @Setup
    public void baseInit() {
        super.initContext();
        repository = context.getBean(EntityWithManyStringFieldsRepository.class);
    }

    @Setup(Level.Iteration)
    public void init() {
        EntityWithManyStringFields entity = new EntityWithManyStringFields();
        entity.setField1(random.nextGaussian() + "");
        entity.setField2(random.nextGaussian() + "");
        entity.setField3(random.nextGaussian() + "");
        entity.setField4(random.nextGaussian() + "");
        entity.setField5(random.nextGaussian() + "");
        entity.setField6(random.nextGaussian() + "");
        entity.setField7(random.nextGaussian() + "");
        entity.setField8(random.nextGaussian() + "");

        repository.save(entity);
    }

    @Setup(Level.Iteration)
    public void initInvocation() {
        entity = repository.findAll().iterator().next();
        entity.setField1(random.nextGaussian() + "");
    }

    @TearDown(Level.Iteration)
    public void teatDown() {
        repository.deleteAll();
    }

    @Benchmark
    public EntityWithManyStringFields measureDirtyChecking() {
        return repository.saveAndFlush(entity);
    }
}
