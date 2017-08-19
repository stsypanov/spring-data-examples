package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DistinctTest extends TestBase {

	@Before
	public void setUp() throws Exception {
		initRandom();
		List<Pupil> pupils = random
				.ints(1000, 1, 400)
				.boxed()
				.map(randomLong -> new Pupil(randomLong, String.valueOf(random.nextInt(100))))
				.collect(Collectors.toList());

		pupilRepository.save(pupils);
	}

	@Test
	public void findWithDistinct() throws Exception {
		List<String> pupils = pupilRepository.findAllNames();
	}

	@Test
	public void findWithHashSetDistinct() throws Exception {
		Set<String> pupils = pupilRepository.findAllNamesAsSet();
	}

}
