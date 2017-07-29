package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static com.luxoft.logeek.entity.listener.PupilListener.MESSAGE;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Commit
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TestExecutionListener.class
})
public class DeleteWithHandlerTest extends TestBase {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        pupilRepository.save(asList(new Pupil(9), new Pupil(10)));
    }

    @Test
    public void deleteAll() {
        exception.expect(RuntimeException.class);
        exception.expectMessage(MESSAGE);
        pupilRepository.deleteAll();
    }

    @Test
    public void deleteAllInBatch() {
        assertFalse(pupilRepository.findAll().isEmpty());

        pupilRepository.deleteAllInBatch();

        assertTrue(pupilRepository.findAll().isEmpty());
    }

}
