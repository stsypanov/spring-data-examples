package com.luxoft.logeek.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.Pupil;
import org.hibernate.jpa.QueryHints;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.transaction.AfterTransaction;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


@Commit
@DatabaseSetup("/BaseJpaRepositoryImplFindOneReadOnlyTest.xml")
public class BaseJpaRepositoryImplFindOneReadOnlyTest extends TestBase {

	private final Long pupilId = 1L;
	private final int newAge = 0;
	
	private boolean readOnly;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testFindOneReadOnlyTrue_expectValueNotUpdated() {
		readOnly = true;
		Pupil pupil = pupilRepository.findOne(pupilId, readOnly);
		pupil.setAge(newAge);
	}

	@Test
	public void testFindOneReadOnlyTrue_useEntityManager_expectValueNotUpdated() {
		expectedException.expect(AssertionError.class);

		readOnly = true;

		Map<String, Object> hints = new HashMap<>();
		hints.put(QueryHints.HINT_READONLY, readOnly);

		Pupil pupil = em.find(Pupil.class, pupilId, hints);
		pupil.setAge(newAge); //todo bug: dirty checking is on, while HINT_READONLY = true
	}

	@Test
	public void testFindOneReadOnlyFalse_expectValueUpdated() {
		readOnly = false;
		pupilRepository.findOne(pupilId, readOnly).setAge(newAge);
	}

	@Test
	public void testFindOneStateless_expectValueNotUpdated() {
		readOnly = true;
		pupilRepository.findOneStateless(pupilId).setAge(newAge);
	}

	@AfterTransaction
	public void afterTransaction() {
		int actualAge = pupilRepository.findById(pupilId).map(Pupil::getAge).orElseThrow(RuntimeException::new);

		if (readOnly) {
			assertThat(newAge, not(actualAge));
		} else {
			assertThat(newAge, equalTo(actualAge));
		}
	}
}