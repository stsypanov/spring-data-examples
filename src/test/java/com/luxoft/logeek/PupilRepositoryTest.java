package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql("/PupilRepositoryTest.sql")
class PupilRepositoryTest extends TestBase {

  /**
   * What instance of Set will be returned in the next two methods
   */
  @Test
  void findUnordered() {
    Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThan(3);
    assertEquals(HashSet.class.getName(), pupilsOlderThan.getClass().getName());
  }

  @Test
  void findOrdered() {
    Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThanOrderedByAge(3);
    assertEquals(LinkedHashSet.class.getName(), pupilsOlderThan.getClass().getName());
  }

  @Test
  void findSorted() {
    Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThanSortedByAge(3);
    assertEquals(HashSet.class.getName(), pupilsOlderThan.getClass().getName());
  }
}
