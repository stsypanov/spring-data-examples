package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import com.luxoft.logeek.repository.ChildRepository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Created by Сергей on 02.04.2017.
 */
public class ChildServiceImplTest extends TestBase{
	@Autowired private ChildService childService;
	@Autowired private ChildRepository childRepository;

	@Test
	public void saveChild() {
		Parent parent = new Parent("mama");
		childService.saveChild(new Child(parent));
	}

	@After
	public void tearDown() {
		List<Child> children = childRepository.findAll();
		assertFalse(children.isEmpty());
	}
}