package com.luxoft.logeek.repository;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.Pupil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.transaction.AfterTransaction;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

@Commit
public class BaseJpaRepositoryImplFindAllReadOnlyFalseTest extends TestBase {
	private final Integer newAge = 0;
	private List<Long> ids;

	@Before
	public void setUp() {
		List<Pupil> pupils = Arrays.asList(
				new Pupil(1),
				new Pupil(2),
				new Pupil(3),
				new Pupil(4),
				new Pupil(5),
				new Pupil(6)
		);
		pupilRepository.save(pupils);

		ids = pupilRepository.findAll().stream().map(Pupil::getId).collect(toList());
		
		pupilRepository.clear();
	}

	@Test
	public void testFindAll_readOnlyFalse_expectValueUpdated() {
		pupilRepository.findAll(ids, false).forEach(pupil -> pupil.setAge(newAge));
	}

	@AfterTransaction
	public void afterTransaction() {
		super.afterTransaction();

		pupilRepository.findAll(ids).stream().map(Pupil::getAge).forEach(age -> {
			assertEquals(newAge, age);
		});
	}

}