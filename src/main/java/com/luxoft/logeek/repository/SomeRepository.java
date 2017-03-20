package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.SomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SomeRepository extends JpaRepository<SomeEntity, Long> {

//	@Query("select e from SomeEntity e where e.value in :values")
//	List<SomeEntity> findByValueIn(@Param("values") List<Long> values); 

	List<SomeEntity> findByValueIn(List<Long> values);

	List<SomeEntity> findByValueIn(Set<Long> values);
}
