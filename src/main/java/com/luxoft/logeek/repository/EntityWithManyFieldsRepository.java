package com.luxoft.logeek.repository;

import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.entity.EntityWithManyFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface EntityWithManyFieldsRepository extends JpaRepository<EntityWithManyFields, Long> {

	Collection<HasIdAndName> findAllByName(String name);

	@Query("select new com.luxoft.logeek.data.HasIdAndNameImpl(e.id, e.name)" +
			" from EntityWithManyFields e " +
			"where e.name = :name")
	Collection<HasIdAndName> findAllByNameUsingObject(@Param("name") String name);
	
}
