package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql("/ChildRepositoryGraphTest.sql")
class ChildRepositoryGraphTest extends TestBase {

  private final Long childId = 1L;

  @Test
  void testGraph_expectFieldInitialized() {
    Child child1 = childRepository.findOne(childId, Child.PARENT);

    boolean initialized = Hibernate.isInitialized(child1.getParent());
    assertTrue(initialized);
  }

  @Test
  void testGraph_expectFieldNotInitialized() {
    Child child1 = childRepository.findById(childId).orElseThrow(NullPointerException::new);

    boolean initialized = Hibernate.isInitialized(child1.getParent());
    assertFalse(initialized);
  }

}
