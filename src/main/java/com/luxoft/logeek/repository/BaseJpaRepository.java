package com.luxoft.logeek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityGraph;
import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	T findOne(ID id, String graphName);

	T findOne(ID id, EntityGraph graph, boolean readOnly);

	T findOne(ID id, String graphName, boolean readOnly);
	
	T findOne(ID id, boolean readOnly);

	T findOneStateless(ID id);
}
