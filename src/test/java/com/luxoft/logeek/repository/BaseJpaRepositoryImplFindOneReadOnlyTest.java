package com.luxoft.logeek.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.TestBase;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.transaction.AfterTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@Commit
@DatabaseSetup("/BaseJpaRepositoryImplFindOneReadOnlyTest.xml")
public class BaseJpaRepositoryImplFindOneReadOnlyTest extends TestBase {

	private final Long pupilId = 1L;
	private final int newAge = 0;
	
	private boolean readOnly;

	@Test
	public void testFindOneReadOnlyTrue_expectValueNotUpdated() {
		readOnly = true;
		pupilRepository.findOne(pupilId, readOnly).setAge(newAge);//bug 
	}

	@Test
	public void testFindOneReadOnlyFalse_expectValueUpdated() {
		readOnly = false;
		pupilRepository.findOne(pupilId, readOnly).setAge(newAge);
	}

	@AfterTransaction
	public void afterTransaction() {
		int actualAge = pupilRepository.findOne(pupilId).getAge();

		if (readOnly) {
			assertNotEquals(newAge, actualAge);
		} else {
			assertEquals(newAge, actualAge);
		}
	}
}