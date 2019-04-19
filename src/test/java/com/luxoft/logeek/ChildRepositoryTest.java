package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Sql("/ChildRepositoryTest.sql")
public class ChildRepositoryTest extends TestBase {

  @Test
  public void testNamedQuery() {
    List<Child> children = childRepository.findByParentName("мама");
    assertEquals(2, children.size());
  }

  @Test
  public void testQueryWithExplicitJoin() {
    List<Child> children = childRepository.findByParentNameWithExplicitJoin("мама");
    assertEquals(2, children.size());
  }

  @Test
  public void testQueryWithoutExplicitJoin() {
    List<Child> children = childRepository.findByParentNameNoExplicitJoin("мама");
    assertEquals(2, children.size());
  }

  @Test
  public void findByParentId() {
    childRepository.findByParentId(1L);
  }

  @Test
  public void findByParentIdWithExplicitJoin() {
    childRepository.findByParentIdWithExplicitJoin(1L);
  }

  @Test
  public void findByParentIdWithoutExplicitJoin() {
    childRepository.findByParentIdWithoutExplicitJoin(1L);
  }

  @Test
  public void testFindWithTemplateFetchingParent() {
    List<Child> children = childRepository.findAll(true, false, false);

    children.forEach(child -> assertTrue(Hibernate.isInitialized(child.getParent())));
  }

  @Test
  public void testFindWithTemplateNotFetchingParent() {
    List<Child> children = childRepository.findAll(false, false, false);

    children.forEach(child -> assertFalse(Hibernate.isInitialized(child.getParent())));
  }

}
