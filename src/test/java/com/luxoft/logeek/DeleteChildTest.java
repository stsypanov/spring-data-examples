package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@Commit
@ActiveProfiles(profiles = "oracle", inheritProfiles = false)
public class DeleteChildTest extends TestBase {

    private Long papaId;

    @Before
    public void setUp() throws Exception {
        Parent papa = new Parent("папа");

        Child child1 = new Child(papa);
        Child child2 = new Child(papa);

        childRepository.save(asList(child1, child2));

        papaId = papa.getId();
    }

    @After
    public void tearDown() throws Exception {
        //do not call super.tearDown();
    }

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
