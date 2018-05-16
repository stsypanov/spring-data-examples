package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class Modifier {
    private final PupilRepository repository;

    public Pupil save(Pupil pupil){
        return repository.save(pupil);
    }

    public Pupil updateAgeWithSave(Long pupilId) {
        Pupil pupil = repository.findById(pupilId).orElseThrow(NullPointerException::new);
        pupil.age++;
        return repository.save(pupil);
    }

    public Pupil updateAge(Long pupilId) {
        Pupil pupil = repository.findById(pupilId).orElseThrow(NullPointerException::new);
        pupil.age++;
        return pupil;
    }
}
