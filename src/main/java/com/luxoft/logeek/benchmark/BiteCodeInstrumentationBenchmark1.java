package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.EntityWithManyStringFields;
import com.luxoft.logeek.repository.EntityWithManyStringFieldsRepository;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

//@BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime, Mode.SingleShotTime})
//@BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime})
@BenchmarkMode(value = {Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class BiteCodeInstrumentationBenchmark1 extends BenchmarkBase {
    private EntityWithManyStringFieldsRepository repository;

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

    @TearDown(Level.Iteration)
    public void teatDown() {
        repository.deleteAll();
    }

    @Benchmark
    public EntityWithManyStringFields measureDirtyChecking() {
        EntityWithManyStringFields entity = repository.findAll().iterator().next();

        entity.setField1(random.nextGaussian() + "");
//        entity.setField2(random.nextGaussian() + "");
//        entity.setField3(random.nextGaussian() + "");
//        entity.setField4(random.nextGaussian() + "");
//        entity.setField5(random.nextGaussian() + "");
//        entity.setField6(random.nextGaussian() + "");
//        entity.setField7(random.nextGaussian() + "");
//        entity.setField8(random.nextGaussian() + "");

        return repository.saveAndFlush(entity);
    }
}
