package com.luxoft.logeek;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("unused")
public class DeleteAllPupilsTest extends TestBase {
    
    /**
     * What queries will be executed?
     */
    @Test
    public void deleteAll() throws Exception {
        pupilRepository.deleteAll();
        assertTrue(pupilRepository.findAll().isEmpty());
    }
}
