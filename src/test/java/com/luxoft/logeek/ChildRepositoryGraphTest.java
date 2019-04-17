package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Sql("/ChildRepositoryGraphTest.sql")
public class ChildRepositoryGraphTest extends TestBase {

  private final Long childId = 1L;

  @Test
  public void testGraph_expectFieldInitialized() {
    Child child1 = childRepository.findOne(childId, Child.PARENT);

    boolean initialized = Hibernate.isInitialized(child1.getParent());
    assertTrue(initialized);
  }

  @Test
  public void testGraph_expectFieldNotInitialized() {
    Child child1 = childRepository.findById(childId).orElseThrow(NullPointerException::new);

    boolean initialized = Hibernate.isInitialized(child1.getParent());
    assertFalse(initialized);
  }

}
