package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@Sql("/PupilRepositoryTest.sql")
public class PupilRepositoryTest extends TestBase {

  /**
   * What instance of Set will be returned in the next two methods
   */
  @Test
  public void findUnordered() {
    Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThan(3);
    assertEquals(HashSet.class.getName(), pupilsOlderThan.getClass().getName());
  }

  @Test
  public void findOrdered() {
    Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThanOrderedByAge(3);
    assertEquals(LinkedHashSet.class.getName(), pupilsOlderThan.getClass().getName());
  }

  @Test
  public void findSorted() {
    Set<Pupil> pupilsOlderThan = pupilRepository.findPupilsOlderThanSortedByAge(3);
    assertEquals(HashSet.class.getName(), pupilsOlderThan.getClass().getName());
  }
}
