package com.luxoft.logeek.another;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.CompositeKey;
import com.luxoft.logeek.entity.another.EntityWithCompositeKey;
import com.luxoft.logeek.repository.another.AnotherEntityWithCompositeKeyRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@DatabaseSetup("/EntityWithCompositeKeyRepositoryTest.xml")
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
	}

	@Test
	public void findAll() {
		List<EntityWithCompositeKey> all = anotherEntityWithCompositeKeyRepository.findAllById(compositeKeys);
	}
}