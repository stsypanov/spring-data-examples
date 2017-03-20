package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChildRepositoryTest extends TestBase {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
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
	public void testFindWithTemplate() throws Exception {
		List<Child> children = childRepository.findUsingTemplate();
		assertFalse(children.isEmpty());
	}
}
