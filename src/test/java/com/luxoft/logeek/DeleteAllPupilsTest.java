package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("unused")
public class DeleteAllPupilsTest extends TestBase {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * What queries will be executed?
     */
    @Test
    public void deleteAll() throws Exception {
        pupilRepository.deleteAll();
        assertTrue(pupilRepository.findAll().isEmpty());
    }

    @Test
    public void deleteOne() throws Exception {
        List<Pupil> all = pupilRepository.findAll();
        pupilRepository.delete(all.iterator().next());
    }

    @Test
    public void deleteAllInBatch() throws Exception {
        List<Pupil> all = pupilRepository.findAll();

        assertFalse(pupilRepository.findAll().isEmpty());

        pupilRepository.deleteAllInBatch();

        assertTrue(pupilRepository.findAll().isEmpty());
    }

}
