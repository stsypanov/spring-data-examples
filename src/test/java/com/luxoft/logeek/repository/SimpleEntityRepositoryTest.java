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
        repository.save(new SimpleEntity(1, "ivan"));

        List<HasIdAndName> coll1 = repository.findAllByName("ivan");

        List<IdAndNameDto> coll2 = repository.findAllByNameUsingDto("ivan");

        assertEquals(coll1.size(), coll2.size());
    }

}