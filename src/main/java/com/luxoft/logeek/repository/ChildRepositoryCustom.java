package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.Child;

import java.util.List;

public interface ChildRepositoryCustom {
	
	List<Child> findUsingTemplate(boolean fetchParent, boolean fetchToys, boolean orderByAge);
}
