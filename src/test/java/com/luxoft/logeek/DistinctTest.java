package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class DistinctTest extends TestBase{
	
	@Before
	public void setUp() throws Exception {
		List<Pupil> pupils = new Random()
				.ints(1000, 0, 400)
				.boxed()
				.map(randomLong -> new Pupil(randomLong, String.valueOf(randomLong)))
				.collect(Collectors.toList());
		
		pupilRepository.save(pupils);
	}

	@Test
	public void findWithDistinct() throws Exception {
		List<Pupil> pupils = pupilRepository.findAllDistinct();//todo distinct does not work if distinct p
	}

	@Test
	public void findWithHashSetDistinct() throws Exception {
		Set<Pupil> pupils = pupilRepository.findAllInSet();
	}

}
