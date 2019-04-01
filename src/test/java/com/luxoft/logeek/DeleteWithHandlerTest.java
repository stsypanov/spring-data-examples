package com.luxoft.logeek;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Commit
@Sql("/DeleteWithHandlerTest.sql")
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
