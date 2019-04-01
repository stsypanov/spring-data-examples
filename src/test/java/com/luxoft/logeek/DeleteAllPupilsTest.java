package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("unused")
public class DeleteAllPupilsTest extends TestBase {

    @Override
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
        pupilRepository.saveAll(pupils);
        pupilRepository.flush();
    }

    /**
     * What queries will be executed?
     */
    @Test
    public void deleteAll() {
        pupilRepository.deleteAll();
        assertTrue(pupilRepository.findAll().isEmpty());
    }

    @Test
    public void deleteOne() {
        List<Pupil> all = pupilRepository.findAll();
        pupilRepository.delete(all.iterator().next());
    }

    @Test
    public void deleteAllInBatch() {
        List<Pupil> all = pupilRepository.findAll();

        assertFalse(pupilRepository.findAll().isEmpty());

        pupilRepository.deleteAllInBatch();

        assertTrue(pupilRepository.findAll().isEmpty());
    }

}
