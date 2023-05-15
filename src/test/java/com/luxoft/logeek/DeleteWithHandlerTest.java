package com.luxoft.logeek;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Commit
@Sql("/DeleteWithHandlerTest.sql")
class DeleteWithHandlerTest extends TestBase {

  private boolean entityListenerUsed;

  @Test
  void deleteAll() {
    entityListenerUsed = true;

    assertThrows(Exception.class, () -> pupilRepository.deleteAll());
  }

  @Test
  void deleteAllInBatch() {
    entityListenerUsed = false;

    pupilRepository.deleteAllInBatch();
  }

  @AfterEach
  void tearDown() {
    if (entityListenerUsed) {
      assertThat(pupilRepository.findAll().size()).isEqualTo(1);
    } else {
      assertThat(pupilRepository.findAll().size()).isEqualTo(0);
    }
  }
}
