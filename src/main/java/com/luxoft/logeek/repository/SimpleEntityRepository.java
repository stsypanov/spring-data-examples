package com.luxoft.logeek.repository;

import com.luxoft.logeek.data.HasIdAndName;
import com.luxoft.logeek.data.IdAndNameDto;
import com.luxoft.logeek.entity.SimpleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SimpleEntityRepository extends BaseJpaRepository<SimpleEntity, Long> {

	List<HasIdAndName> findAllByName(String name);

	@Query("select new com.luxoft.logeek.data.IdAndNameDto(e.id, e.name)" +
			" from SimpleEntity e " +
			"where e.name = :name")
	List<IdAndNameDto> findAllByNameUsingDto(@Param("name") String name);

}
