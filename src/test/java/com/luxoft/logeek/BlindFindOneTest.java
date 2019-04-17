package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertNotNull;

@Sql("/BlindFindOneTest.sql")
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

  @After
  public void after() {
    final Child child = childRepository.findById(childId).orElse(null);
    assertNotNull(child);

    assertNotNull(child.getParent());
  }
}
