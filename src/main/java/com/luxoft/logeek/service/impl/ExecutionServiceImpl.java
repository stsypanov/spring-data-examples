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
    public void setUp(int entityCount) {
        List<EntityWithManyStringFields> items = random
                .ints(entityCount)
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
    public List<EntityWithManyStringFields> executeFieldModification() {
        List<EntityWithManyStringFields> entities = repository.findAll();
        entities.forEach(entity -> entity.setField1(random.nextGaussian() + ""));

        return repository.save(entities);
    }

    @Override
    public List<EntityWithManyStringFields> executeWithoutFieldModification() {
        List<EntityWithManyStringFields> entities = repository.findAll();
        return repository.save(entities);
    }

    @Override
    public void tearDown() {
        repository.deleteAllInBatch();
    }
}
