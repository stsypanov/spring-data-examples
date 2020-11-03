package com.luxoft.logeek.repository;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.data.IdAndNameDto;
import com.luxoft.logeek.entity.SimpleEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleEntityRepositoryTest extends TestBase {

  @Autowired
  private SimpleEntityRepository repository;

  @Test
  public void findAllByName() {
    repository.save(new SimpleEntity(1, "ivan", "","","","","",""));

    List<HasIdAndName> dtos = repository.findAllByName("ivan");

    List<IdAndNameDto> projections = repository.findAllByNameUsingDto("ivan");

    assertEquals(dtos.size(), projections.size());
  }

}
