package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Commit
public class InstrumentationTest extends TestBase {
	

	@Before
	@Override
	public void setUp() {
		super.setUp();
		List<Pupil> pupils = random
				.ints(5000, 0, 400)
				.boxed()
				.map(randomLong -> new Pupil(randomLong, String.valueOf(random.nextInt(100))))
				.collect(toList());

		pupilRepository.saveAll(pupils);
	}

	@Test
	public void name() throws Exception {
		List<Pupil> pupils = pupilRepository.findAll();
		List<Pupil> updatedPupils = pupils.stream()
				.peek(pupil -> {
					pupil.setAge(random.nextInt(100));
					pupil.setEnrolled(LocalDate.now().minusDays(random.nextInt(100)));
					pupil.setName(random.nextInt(100) + "");
				})
				.collect(toList());

		pupilRepository.saveAll(updatedPupils);
		pupilRepository.flush();

//		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//		Thread.sleep(TimeUnit.MINUTES.toMillis(2));
	}
}
