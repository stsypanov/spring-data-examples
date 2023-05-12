package com.luxoft.logeek.repository;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.data.IdAndNameDto;
import com.luxoft.logeek.entity.SimpleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleEntityRepositoryTest extends TestBase {

  @Autowired
  private SimpleEntityRepository repository;

  @Test
  void findAllByName() {
    repository.save(new SimpleEntity(1, "ivan", "", "", "", "", "", ""));

    List<HasIdAndName> projections = repository.findAllByName("ivan");

    List<IdAndNameDto> dtos = repository.findAllByNameUsingDto("ivan");

    assertEquals(projections.size(), dtos.size());
  }

}
