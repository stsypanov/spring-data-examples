package com.luxoft.logeek;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.entity.Child;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.transaction.AfterTransaction;

import static org.junit.Assert.assertNotNull;

@Commit
@DatabaseSetup("/BlindFindOneTest.xml")
public class BlindFindOneTest extends TestBase {

    private Long childId;
    private Long parentId = 1L;

    @Test
    public void testFindOne() {
        Child child = new Child();
        parentRepository.findById(parentId).ifPresent(child::setParent);

        childId = childRepository.save(child).getId();
    }

    @Test
    public void testGetOne() {
        Child child = new Child();
        child.setParent(parentRepository.getOne(parentId));

        childId = childRepository.save(child).getId();
    }

    @AfterTransaction
    public void after() {
        final Child child = childRepository.findById(childId).orElse(null);
        assertNotNull(child);

        assertNotNull(child.getParent());
    }
}
