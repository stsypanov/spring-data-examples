package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.entity.EntityWithManyStringFields;
import com.luxoft.logeek.repository.EntityWithManyStringFieldsRepository;
import com.luxoft.logeek.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@Component
@Transactional
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService {
    private final EntityWithManyStringFieldsRepository repository;
    private final Random random = new Random();

    @Override
    public void setUp(int size) {
        List<EntityWithManyStringFields> items = random
                .ints(size)
                .boxed()
                .map(rnd -> {
                    EntityWithManyStringFields entity = new EntityWithManyStringFields();
                    entity.setField1(random.nextGaussian() + "");
                    entity.setField2(random.nextGaussian() + "");
                    entity.setField3(random.nextGaussian() + "");
                    entity.setField4(random.nextGaussian() + "");
                    entity.setField5(random.nextGaussian() + "");
                    entity.setField6(random.nextGaussian() + "");
                    entity.setField7(random.nextGaussian() + "");
                    entity.setField8(random.nextGaussian() + "");
                    return entity;
                })
                .collect(toList());

        repository.save(items);
    }

    @Override
    public List<EntityWithManyStringFields> execute() {
        List<EntityWithManyStringFields> entities = repository.findAll();
        entities = entities.stream()
                .peek(entity -> {
                    entity.setField1(random.nextGaussian() + "");
//					entity.setField2(random.nextGaussian() + "");
//					entity.setField3(random.nextGaussian() + "");
//					entity.setField4(random.nextGaussian() + "");
//					entity.setField5(random.nextGaussian() + "");
//					entity.setField6(random.nextGaussian() + "");
//					entity.setField7(random.nextGaussian() + "");
//					entity.setField8(random.nextGaussian() + "");
                })
                .collect(toList());

        return repository.save(entities);
    }

    @Override
    public void tearDown() {
        repository.deleteAllInBatch();
    }
}
