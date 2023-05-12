package com.luxoft.logeek;

import com.luxoft.logeek.entity.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Sql("/DeleteChildTest.sql")
class DeleteChildTest extends TestBase {

  private Long papaId = 1L;

  @Test
  void deleteAllInBatch_expectParentIsNotDeleted() {
    childRepository.deleteAllInBatch();

    Parent papa = parentRepository.findById(papaId).orElse(null);

    assertNotNull(papa);
  }

  @Test
  void deleteAll_expectParentIsDeleted() {
    childRepository.deleteAll();//проверить, почему в лог записан update

    Parent papa = parentRepository.findById(papaId).orElse(null);

    assertNull(papa);
  }

}
