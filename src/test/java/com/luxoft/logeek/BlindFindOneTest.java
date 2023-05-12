package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Sql("/BlindFindOneTest.sql")
class BlindFindOneTest extends TestBase {

  private Long childId;
  private Long parentId = 1L;

  @Test
  void testFindOne() {
    Child child = new Child();
    parentRepository.findById(parentId).ifPresent(child::setParent);

    childId = childRepository.save(child).getId();
  }

  @Test
  void testGetOne() {
    Child child = new Child();
    child.setParent(parentRepository.getOne(parentId));

    childId = childRepository.save(child).getId();
  }

  @AfterEach
  void after() {
    final Child child = childRepository.findById(childId).orElse(null);
    assertNotNull(child);

    assertNotNull(child.getParent());
  }
}
