package com.luxoft.logeek.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.Pupil;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.transaction.AfterTransaction;

import java.util.List;

import static com.google.common.primitives.Longs.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Commit
@DatabaseSetup("/BaseJpaRepositoryImplFindAllReadOnlyTest.xml")
public class BaseJpaRepositoryImplFindAllReadOnlyTest extends TestBase {
	private final Integer newAge = 0;

	private List<Long> ids = asList(1L, 2L, 3L);
	private boolean readOnly;

	@Test
	public void testFindAll_readOnlyFalse_expectValueUpdated() {
		readOnly = false;
		pupilRepository.findAll(ids, readOnly).forEach(pupil -> pupil.setAge(newAge));
	}

	@Test
	public void testFindAll_readOnlyTrue_expectValueNotUpdated() {
		readOnly = true;
		pupilRepository.findAll(ids, true).forEach(pupil -> pupil.setAge(newAge));
	}

	@AfterTransaction
	public void afterTransaction() {
		super.afterTransaction();

		if (readOnly) {
			assertNotModified();
		} else {
			assertModified();
		}
	}

	private void assertNotModified() {
		pupilRepository.findAll(ids).stream().map(Pupil::getAge).forEach(age -> {
			assertNotEquals(newAge, age);
		});
	}

	private void assertModified() {
		pupilRepository.findAll(ids).stream().map(Pupil::getAge).forEach(age -> {
			assertEquals(newAge, age);
		});
	}

}