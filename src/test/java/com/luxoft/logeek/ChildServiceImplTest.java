package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Сергей on 02.04.2017.
 */
@Sql("/ChildServiceImplTest.sql")
public class ChildServiceImplTest extends TestBase {
  @Autowired
  private ChildService childService;

  @Test
  public void newChildForParent() {
    childService.newChildForParent(1L);
  }

  @Test
  public void optimizedNewChildForParent() {
    childService.optimizedNewChildForParent(1L);
  }

  @After
  public void tearDown() {
    List<Child> children = childRepository.findAll();
    assertEquals(children.size(), 1);
  }
}
