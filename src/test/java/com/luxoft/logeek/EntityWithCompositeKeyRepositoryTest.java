package com.luxoft.logeek;

import com.luxoft.logeek.entity.CompositeKey;
import com.luxoft.logeek.entity.EntityWithCompositeKey;
import com.luxoft.logeek.repository.EntityWithCompositeKeyRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class EntityWithCompositeKeyRepositoryTest extends TestBase {

	@Autowired
	private EntityWithCompositeKeyRepository entityWithCompositeKeyRepository;

	private List<CompositeKey> compositeKeys;

	@Override
	@Before
	public void setUp() {
		CompositeKey key1 = new CompositeKey(1L, 1L);
		CompositeKey key2 = new CompositeKey(2L, 2L);
		CompositeKey key3 = new CompositeKey(3L, 3L);
		CompositeKey key4 = new CompositeKey(4L, 4L);
		CompositeKey key5 = new CompositeKey(5L, 5L);

		compositeKeys = Arrays.asList(key1, key2, key3, key4, key5);

		EntityWithCompositeKey e1 = new EntityWithCompositeKey(key1);
		EntityWithCompositeKey e2 = new EntityWithCompositeKey(key2);
		EntityWithCompositeKey e3 = new EntityWithCompositeKey(key3);
		EntityWithCompositeKey e4 = new EntityWithCompositeKey(key4);
		EntityWithCompositeKey e5 = new EntityWithCompositeKey(key5);

		entityWithCompositeKeyRepository.save(Arrays.asList(e1, e2, e3, e4, e5));
	}

	@Test
	public void findAll() {
		List<EntityWithCompositeKey> all = entityWithCompositeKeyRepository.findAll(compositeKeys);
	}
}