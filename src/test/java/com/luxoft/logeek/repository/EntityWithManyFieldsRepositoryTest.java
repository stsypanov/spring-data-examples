package com.luxoft.logeek.repository;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.data.IdAndNameDto;
import com.luxoft.logeek.entity.EntityWithManyFields;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EntityWithManyFieldsRepositoryTest extends TestBase {

    @Autowired
    private EntityWithManyFieldsRepository repository;

    @Test
    public void findAllByName() {
        repository.save(new EntityWithManyFields(1, "ivan"));

        List<HasIdAndName> coll1 = repository.findAllByName("ivan");

        List<IdAndNameDto> coll2 = repository.findAllByNameUsingDto("ivan");

        assertEquals(coll1.size(), coll2.size());
    }

}