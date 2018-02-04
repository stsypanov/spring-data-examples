package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Commit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Ignore
@Commit
public class ChildRepositoryGraphTest extends TestBase {

	private Long childId;

	@Override
	@Before
	public void setUp() {
		Parent papa = new Parent("папа");

		Child child1 = new Child(papa);

		childRepository.save(child1);

		childId = child1.getId();
	}

	@Test
	public void testGraph_expectFieldInitialized() {
		Child child1 = childRepository.findOne(childId, Child.PARENT);

		boolean initialized = Hibernate.isInitialized(child1.getParent());
		assertTrue(initialized);
	}

	@Test
	public void testGraph_expectFieldNotInitialized() {
		Child child1 = childRepository.findOne(childId);

		boolean initialized = Hibernate.isInitialized(child1.getParent());
		assertFalse(initialized);
	}

	@After
	public void tearDown() {
		childRepository.deleteAll();
	}
}
