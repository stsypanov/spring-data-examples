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
	public void setUp() throws Exception {
		CompositeKey key1 = new CompositeKey(1, 1);
		CompositeKey key2 = new CompositeKey(2, 2);
		CompositeKey key3 = new CompositeKey(3, 3);
		CompositeKey key4 = new CompositeKey(4, 4);
		CompositeKey key5 = new CompositeKey(5, 5);

		compositeKeys = Arrays.asList(key1, key2, key3, key4, key5);

		EntityWithCompositeKey e1 = new EntityWithCompositeKey(1, 1);
		EntityWithCompositeKey e2 = new EntityWithCompositeKey(2, 2);
		EntityWithCompositeKey e3 = new EntityWithCompositeKey(3, 3);
		EntityWithCompositeKey e4 = new EntityWithCompositeKey(4, 4);
		EntityWithCompositeKey e5 = new EntityWithCompositeKey(5, 5);

		anotherEntityWithCompositeKeyRepository.save(Arrays.asList(e1, e2, e3, e4, e5));
	}

	@Test
	public void findAll() throws Exception {
		List<EntityWithCompositeKey> all = anotherEntityWithCompositeKeyRepository.findAll(compositeKeys);
	}
}