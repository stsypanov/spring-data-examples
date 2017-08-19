package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unused")
public class PupilRepositoryTest extends TestBase {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * What instance of Set will be returned in the next two methods
     */
    @Test
    public void findUnordered() throws Exception {
        Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThan(3);
        assertEquals(HashSet.class.getName(), pupilsOlderThan.getClass().getName());
    }

    @Test
    public void findOrdered() throws Exception {
        Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThanOrderedByAge(3);
        assertEquals(LinkedHashSet.class.getName(), pupilsOlderThan.getClass().getName());
    }

    @Test
    public void findSorted() throws Exception {
        Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThanSortedByAge(3);
        assertEquals(LinkedHashSet.class.getName(), pupilsOlderThan.getClass().getName());
    }
}
