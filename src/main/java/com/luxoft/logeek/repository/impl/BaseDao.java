package com.luxoft.logeek.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

abstract class BaseDao {
	
	@PersistenceContext
	protected EntityManager em;
}
