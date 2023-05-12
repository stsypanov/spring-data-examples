package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class DistinctTest extends TestBase {

  @BeforeEach
  public void setUp() {
    initRandom();
    List<Pupil> pupils = random
      .ints(1000, 1, 400)
      .boxed()
      .map(randomLong -> new Pupil(randomLong, String.valueOf(random.nextInt(100))))
      .collect(Collectors.toList());

    pupilRepository.saveAll(pupils);
  }

  @Test
  void findWithDistinct() {
    List<String> pupils = pupilRepository.findAllNames();
  }

  @Test
  void findWithHashSetDistinct() {
    Set<String> pupils = pupilRepository.findAllNamesAsSet();
  }

}
