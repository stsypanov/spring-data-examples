package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Sql("/ChildRepositoryTest.sql")
class ChildRepositoryTest extends TestBase {

  @Test
  void testNamedQuery() {
    List<Child> children = childRepository.findByParentName("mother");
    assertEquals(2, children.size());
  }

  @Test
  void testQueryWithExplicitJoin() {
    List<Child> children = childRepository.findByParentNameWithExplicitJoin("mother");
    assertEquals(2, children.size());
  }

  @Test
  void testQueryWithoutExplicitJoin() {
    List<Child> children = childRepository.findByParentNameNoExplicitJoin("mother");
    assertEquals(2, children.size());
  }

  @Test
  void findByParentId() {
    childRepository.findByParentId(1L);
  }

  @Test
  void findByParentIdWithExplicitJoin() {
    childRepository.findByParentIdWithExplicitJoin(1L);
  }

  @Test
  void findByParentIdWithoutExplicitJoin() {
    childRepository.findByParentIdWithoutExplicitJoin(1L);
  }

  @Test
  void testFindWithTemplateFetchingParent() {
    List<Child> children = childRepository.findAll(true, false, false);

    children.forEach(child -> assertTrue(Hibernate.isInitialized(child.getParent())));
  }

  @Test
  void testFindWithTemplateNotFetchingParent() {
    List<Child> children = childRepository.findAll(false, false, false);

    children.forEach(child -> assertFalse(Hibernate.isInitialized(child.getParent())));
  }

  @Test
  void findByParentIds() {
    assertThat(childRepository.findAllByParentIds(List.of())).asList().isEmpty();
    assertThat(childRepository.findAllByParentIds(List.of(2L))).asList().hasSize(2);
  }

  @Test
  void findAllByParentIds_dontFilterIfEmpty() {
    assertThat(childRepository.findAllByParentIds_dontFilterIfEmpty(List.of())).asList().hasSize(6);
    assertThat(childRepository.findAllByParentIds_dontFilterIfEmpty(List.of(2L))).asList().hasSize(2);
  }
}
