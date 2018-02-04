package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.test.annotation.Commit;

import static java.util.Arrays.asList;

@Commit
@Ignore
public class ChildRepositoryTest extends TestBase {

	private Long childId;

	@Override
	@Before
	@Commit
	public void setUp() {
		Parent papa = new Parent("папа");
		Parent mama = new Parent("мама");

		Child child1 = new Child(papa);
		Child child2 = new Child(papa);
		Child child3 = new Child(papa);
		Child child4 = new Child(papa);

		Child child5 = new Child(mama);
		Child child6 = new Child(mama);

		childRepository.save(asList(child1, child2, child3, child4, child5, child6));

		childId = child1.getId();
	}

	/*@Test
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
		List<Child> children = childRepository.findByParentNameNoExplicitJoin("мама");
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
		List<Child> children = childRepository.findUsingTemplate(false, false, false);
		assertFalse(children.isEmpty());
	}*/

	
}
