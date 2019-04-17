package com.luxoft.logeek.repository;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.misc.OracleConstants;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertNotNull;

@ActiveProfiles(value = "oracle", inheritProfiles = false)//todo h2 must support oracle "in" restriction
public class BaseJpaRepositoryImplTest extends TestBase {

  private List<Long> ids;

  @Before
  public void setUp() {
    ids = LongStream.range(1, OracleConstants.MAX_IN_COUNT * 3)
      .boxed()
      .collect(Collectors.toList());
  }

  @Test
  public void findAll() {
    List<Child> all = childRepository.findAllById(ids);

    assertNotNull(all);
  }

}
