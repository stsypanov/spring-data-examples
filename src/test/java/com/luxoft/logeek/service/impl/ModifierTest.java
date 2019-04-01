package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.Pupil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.transaction.AfterTransaction;

import static org.junit.Assert.assertEquals;

@Commit
@Sql("/ModifierTest.sql")
public class ModifierTest extends TestBase {
    @Autowired
    private Modifier modifier;

    private final Long id1 = 1L;
    private final Long id2 = 2L;
    private boolean modified1;
    private boolean modified2;

    @Test
    public void updateAgeWithSave() {
        modifier.updateAgeWithSave(id1);
        modified1 = true;
    }

    @Test
    public void updateAge() {
        modifier.updateAge(id2);
        modified2 = true;
    }

    @AfterTransaction
    public void afterTransaction() {
        super.afterTransaction();
        if (modified1) {
            Pupil pupil = pupilRepository.findById(id1).orElseThrow(NullPointerException::new);
            assertEquals(10, pupil.getAge());
        }
        if (modified2) {
            Pupil pupil = pupilRepository.findById(id2).orElseThrow(NullPointerException::new);
            assertEquals(10, pupil.getAge());
        }
    }
}