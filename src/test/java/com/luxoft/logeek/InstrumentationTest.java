package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import com.luxoft.logeek.entity.Pupil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Commit
class InstrumentationTest extends TestBase {


  @BeforeEach
  @Override
  public void setUp() {
    super.setUp();
    Parent papa = new Parent("папа");
    Parent mama = new Parent("мама");

    Child child1 = new Child(papa);
    Child child2 = new Child(papa);
    Child child3 = new Child(papa);
    Child child4 = new Child(papa);

    Child child5 = new Child(mama);
    Child child6 = new Child(mama);

    childRepository.saveAll(Arrays.asList(child1, child2, child3, child4, child5, child6));
    childRepository.flush();

    em.clear();

    List<Pupil> pupils = random
      .ints(5000, 0, 400)
      .boxed()
      .map(randomLong -> new Pupil(randomLong, String.valueOf(random.nextInt(100))))
      .collect(toList());

    pupilRepository.saveAll(pupils);
  }

  @Test
  void name() {
    List<Pupil> pupils = pupilRepository.findAll();
    List<Pupil> updatedPupils = pupils.stream()
      .peek(pupil -> {
        pupil.setAge(random.nextInt(100));
        pupil.setEnrolled(LocalDate.now().minusDays(random.nextInt(100)));
        pupil.setName(random.nextInt(100) + "");
      })
      .collect(toList());

    pupilRepository.saveAll(updatedPupils);
    pupilRepository.flush();
  }
}
