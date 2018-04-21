package com.luxoft.logeek.another;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.CompositeKey;
import com.luxoft.logeek.entity.another.EntityWithCompositeKey;
import com.luxoft.logeek.repository.another.AnotherEntityWithCompositeKeyRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class AnotherEntityWithCompositeKeyRepositoryTest extends TestBase {
	
	@Autowired
	private AnotherEntityWithCompositeKeyRepository anotherEntityWithCompositeKeyRepository;

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

		EntityWithCompositeKey e1 = new EntityWithCompositeKey(1L, 1L);
		EntityWithCompositeKey e2 = new EntityWithCompositeKey(2L, 2L);
		EntityWithCompositeKey e3 = new EntityWithCompositeKey(3L, 3L);
		EntityWithCompositeKey e4 = new EntityWithCompositeKey(4L, 4L);
		EntityWithCompositeKey e5 = new EntityWithCompositeKey(5L, 5L);

		anotherEntityWithCompositeKeyRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5));
	}

	@Test
	public void findAll() {
		List<EntityWithCompositeKey> all = anotherEntityWithCompositeKeyRepository.findAllById(compositeKeys);
	}
}