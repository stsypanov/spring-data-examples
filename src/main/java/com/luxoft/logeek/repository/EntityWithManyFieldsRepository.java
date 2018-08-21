package com.luxoft.logeek.repository;

import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.data.IdAndNameDto;
import com.luxoft.logeek.entity.EntityWithManyFields;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntityWithManyFieldsRepository extends BaseJpaRepository<EntityWithManyFields, Long> {

	List<HasIdAndName> findAllByName(String name);

	@Query("select new com.luxoft.logeek.data.IdAndNameDto(e.id, e.name)" +
			" from EntityWithManyFields e " +
			"where e.name = :name")
	List<IdAndNameDto> findAllByNameUsingDto(@Param("name") String name);

}
