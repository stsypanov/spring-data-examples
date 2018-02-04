package com.luxoft.logeek;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.entity.Parent;
import org.junit.Test;
import org.springframework.test.annotation.Commit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@Commit
@DatabaseSetup("/DeleteChildTest.xml")
public class DeleteChildTest extends TestBase {

    private Long papaId = 1L;

    @Test
    public void deleteAllInBatch_expectParentIsNotDeleted() {
        childRepository.deleteAllInBatch();

        Parent papa = parentRepository.findOne(papaId);

        assertNotNull(papa);
    }

    @Test
    public void deleteAll_expectParentIsDeleted() {
        childRepository.deleteAll();//проверить, почему в лог записан update

        Parent papa = parentRepository.findOne(papaId);

        assertNull(papa);
    }

}
