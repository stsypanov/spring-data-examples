package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Сергей on 02.04.2017.
 */
@Sql("/ChildServiceImplTest.sql")
class ChildServiceImplTest extends TestBase {
  @Autowired
  private ChildService childService;

  @Test
  void newChildForParent() {
    childService.newChildForParent(1L);
  }

  @Test
  void optimizedNewChildForParent() {
    childService.optimizedNewChildForParent(1L);
  }

  @AfterEach
  void tearDown() {
    List<Child> children = childRepository.findAll();
    assertEquals(children.size(), 1);
  }
}
