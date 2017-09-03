package com.luxoft.logeek;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.entity.Pupil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.annotation.Commit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Commit
@DatabaseSetup("/DeleteWithHandlerTest.xml")
public class DeleteWithHandlerTest extends TestBase {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private boolean entityListenerUsed;

    @Test(expected = Exception.class)
    public void deleteAll() {
        entityListenerUsed = true;

        exception.expect(Exception.class);
        pupilRepository.deleteAll();
    }

    @Test
    public void deleteAllInBatch() {
        entityListenerUsed = false;

        pupilRepository.deleteAllInBatch();
    }

    @After
    public void tearDown() {
        if (entityListenerUsed) {
            assertThat(pupilRepository.findAll().size(), is(1));
        } else {
            assertThat(pupilRepository.findAll().size(), is(0));
        }
    }
}
