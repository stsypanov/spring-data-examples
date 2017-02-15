package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.PupilRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PupilRepositoryTest extends TestBase {
    
    @Autowired private PupilRepository repository;

    @Before
    public void setUp() throws Exception {
        List<Pupil> pupils = Arrays.asList(
                new Pupil(1),
                new Pupil(2),
                new Pupil(3),
                new Pupil(4),
                new Pupil(5),
                new Pupil(6)
        );
        repository.save(pupils);
        repository.flush();
        em.clear();
    }

    /**
     * What instance of Set will be returned in the next two methods
     */
    @Test
    public void findUnordered() throws Exception {
        Set<Pupil> pupilsOlderThan = repository.findPupilsOlderThan(3);
    }

    @Test
    public void findOrdered() throws Exception {
        Set<Pupil> pupilsOlderThan = repository.findPupilsOlderThan(3);
    }

    /**
     * What queries will be executed
     */
    @Test
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void deleteAllInBatch() throws Exception {
        repository.deleteAllInBatch();
    }
}
