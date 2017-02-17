package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.PupilRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class PupilRepositoryTest extends TestBase {
    
    /**
     * What instance of Set will be returned in the next two methods
     */
    @Test
    public void findUnordered() throws Exception {
        Set<Pupil> pupilsOlderThan = repository.findPupilsOlderThan(3);
    }

    @Test
    public void findOrdered() throws Exception {
        Set<Pupil> pupilsOlderThan = repository.findPupilsOlderThanOrderedByAge(3);
    }
}
