package com.luxoft.logeek;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.entity.CompositeKey;
import com.luxoft.logeek.repository.EntityWithCompositeKeyRepository;
import com.luxoft.logeek.repository.another.AnotherEntityWithCompositeKeyRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@DatabaseSetup("/EntityWithCompositeKeyRepositoryTest.xml")
public class EntityWithCompositeKeyRepositoryTest extends TestBase {

    @Autowired
    private EntityWithCompositeKeyRepository entityWithCompositeKeyRepository;
    @Autowired
    private AnotherEntityWithCompositeKeyRepository anotherEntityWithCompositeKeyRepository;

    private List<CompositeKey> compositeKeys;

    @Before
    public void setUp() {
        compositeKeys = Arrays.asList(
                new CompositeKey(1L, 1L),
                new CompositeKey(2L, 2L),
                new CompositeKey(3L, 3L),
                new CompositeKey(4L, 4L),
                new CompositeKey(5L, 5L)
        );
    }

    @Test//case for @EmbeddedId
    public void findAll() {
        entityWithCompositeKeyRepository.findAllById(compositeKeys);
    }

    @Test//case for @Id @Id
    public void _findAll() {
        anotherEntityWithCompositeKeyRepository.findAllById(compositeKeys);
    }
}