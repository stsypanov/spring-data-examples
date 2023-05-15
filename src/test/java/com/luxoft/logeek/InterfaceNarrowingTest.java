package com.luxoft.logeek;

import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterfaceNarrowingTest extends TestBase {

  private static final Long TWO = 2L;
  private static final Long THREE = 3L;
  private static final Long NINE = 9L;
  private static final Long FIVE = 5L;
  private static final Long SEVEN = 7L;

  @Autowired
  private SomeRepository repository;

  @Override
  @BeforeEach
  public void setUp() {
    initRandom();
    List<SomeEntity> entities = random.longs(50)
      .boxed()
      .map(id -> new SomeEntity(id, getValueForId(id)))
      .collect(Collectors.toList());

    repository.saveAll(entities);
  }

  private Long getValueForId(Long id) {
    if (id % 2 == 0) {
      return TWO;
    } else if (id % 3 == 0) {
      return THREE;
    } else if (id % 5 == 0) {
      return FIVE;
    } else if (id % 7 == 0) {
      return SEVEN;
    } else if (id % 9 == 0) {
      return NINE;
    } else {
      return 0L;
    }
  }

  @Test
  void findByIdsWithPossibleDuplicates() {
    List<Long> idList = Arrays.asList(TWO, TWO, THREE, SEVEN, FIVE, NINE, THREE, SEVEN, NINE);
    Set<Long> idSet = new HashSet<>(idList);

    List<SomeEntity> byIdList = repository.findBySomeValueIn(idList);
    List<SomeEntity> byIdSet = repository.findBySomeValueIn(idSet);

    assertEquals(byIdList, byIdSet);
  }
}
