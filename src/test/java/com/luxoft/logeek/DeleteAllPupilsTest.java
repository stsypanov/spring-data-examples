package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("unused")
class DeleteAllPupilsTest extends TestBase {

  @Override
  @BeforeEach
  public void setUp() {
    List<Pupil> pupils = Arrays.asList(
      new Pupil(1),
      new Pupil(2),
      new Pupil(3),
      new Pupil(4),
      new Pupil(5),
      new Pupil(6)
    );
    pupilRepository.saveAll(pupils);
    pupilRepository.flush();
  }

  /**
   * What queries will be executed?
   */
  @Test
  void deleteAll() {
    pupilRepository.deleteAll();
    assertTrue(pupilRepository.findAll().isEmpty());
  }

  @Test
  void deleteOne() {
    List<Pupil> all = pupilRepository.findAll();
    pupilRepository.delete(all.iterator().next());
  }

  @Test
  void deleteAllInBatch() {
    List<Pupil> all = pupilRepository.findAll();

    assertFalse(pupilRepository.findAll().isEmpty());

    pupilRepository.deleteAllInBatch();

    assertTrue(pupilRepository.findAll().isEmpty());
  }

}
