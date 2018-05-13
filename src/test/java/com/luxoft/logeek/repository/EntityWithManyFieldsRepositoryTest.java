package com.luxoft.logeek.repository;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.entity.EntityWithManyFields;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class EntityWithManyFieldsRepositoryTest extends TestBase{
	
	@Autowired
	private EntityWithManyFieldsRepository repository;
	
	@Test
	public void findAllByName() {
		repository.save(new EntityWithManyFields(1L, "1", "ivan"));
		
		
		Collection<HasIdAndName> ivans = repository.findAllByName("ivan");
		
		ivans = repository.findAllByNameUsingObject("ivan");
	}

}