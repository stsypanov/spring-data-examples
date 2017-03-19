package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.SomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SomeRepository extends JpaRepository<SomeEntity, Long> {

//todo report bug about non-working inspection: no warning about narrower interface (List instead of Collection) 
//	@Query("select e from SomeEntity e where e.value in :values")
//	List<SomeEntity> findByValueIn(@Param("values") List<Long> values); 

	List<SomeEntity> findByValueIn(List<Long> values);

	List<SomeEntity> findByValueIn(Set<Long> values);
}
