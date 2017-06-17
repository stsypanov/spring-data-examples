package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.PupilRepository;
import com.luxoft.logeek.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@Component
@Transactional
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService {
	private final PupilRepository pupilRepository;
	private final Random random = new Random();
	
	@Override
	public void execute() {
		List<Pupil> pupils = pupilRepository.findAll();
		
		List<Pupil> updatedPupils = pupils.stream()
				.peek(pupil -> {
					pupil.setAge(random.nextInt(20));
//					pupil.setEnrolled(LocalDate.now().minusDays(random.nextInt(100)));
//					pupil.setName(random.nextInt(100) + "");
				})
				.collect(toList());

		pupilRepository.save(updatedPupils);
	}

	@Override
	public void setUp() {
		List<Pupil> pupils = random
				.ints(5000, 0, 400)
				.boxed()
				.map(randomLong -> new Pupil(randomLong, String.valueOf(random.nextInt(100))))
				.collect(toList());

		pupilRepository.save(pupils);
	}
}
