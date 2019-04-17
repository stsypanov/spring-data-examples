package com.luxoft.logeek.service.datajpa1261;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import com.luxoft.logeek.repository.ToyRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.transaction.AfterTransaction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@Commit
@Sql("/ChildUpdaterTest.sql")
public class ChildUpdaterTest extends TestBase {
  private static final long childId = 1L;

  @Autowired
  private ChildUpdater childUpdater;
  @Autowired
  private ToyRepository toyRepository;

  @Test
  public void updateChild() {
    short age = 10;
    Parent parent = new Parent("папа");
    childUpdater.updateChild(childId, age, parent);
  }

  @Test
  public void updateChildIncorrectly() {
    short age = 10;
    Parent parent = new Parent("папа");
    childUpdater.updateChildIncorrectly(childId, age, parent);
  }

  @AfterTransaction
  public void afterTransaction() {
    Child child = childRepository.findOne(childId, Child.TOYS);

    assertNotNull(child.getParent());
    assertThat(child.getToys().size(), is(1));

    // prevent constraint violation when after 1st test Toy table has one row
    childRepository.deleteAll();
    toyRepository.deleteAll();
  }
}
