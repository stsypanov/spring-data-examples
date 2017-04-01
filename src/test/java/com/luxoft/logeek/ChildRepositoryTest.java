package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ChildRepositoryTest extends TestBase {

	@Override
	@Before
	public void setUp() throws Exception {
		Parent papa = new Parent("папа");
		Parent mama = new Parent("мама");

		Child child1 = new Child(papa);
		Child child2 = new Child(papa);
		Child child3 = new Child(papa);
		Child child4 = new Child(papa);

		Child child5 = new Child(mama);
		Child child6 = new Child(mama);

		childRepository.save(asList(child1, child2, child3, child4, child5, child6));
		childRepository.flush();
	}

	@Test
	public void testNamedQuery() throws Exception {
		List<Child> children = childRepository.findByParentName("мама");
		assertEquals(2, children.size());
	}

	@Test
	public void testQueryWithExplicitJoin() throws Exception {
		List<Child> children = childRepository.findByParentNameWithExplicitJoin("мама");
		assertEquals(2, children.size());
	}

	@Test
	public void testQueryWithoutExplicitJoin() throws Exception {
		List<Child> children = childRepository.findByParentNameWithoutExplicitJoin("мама");
		assertEquals(2, children.size());
	}

	@Test
	public void findByParentId() {
		childRepository.findByParentId(1L);
	}

	@Test
	public void findByParentIdWithExplicitJoin() {
		childRepository.findByParentIdWithExplicitJoin(1L);
	}

	@Test
	public void findByParentIdWithoutExplicitJoin() {
		childRepository.findByParentIdWithoutExplicitJoin(1L);
	}

	@Test
	public void testFindWithTemplate() throws Exception {
		List<Child> children = childRepository.findUsingTemplate();
		assertFalse(children.isEmpty());
	}
}
