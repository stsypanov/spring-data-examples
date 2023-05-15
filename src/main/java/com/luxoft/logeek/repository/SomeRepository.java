package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.SomeEntity;

import java.util.List;
import java.util.Set;

public interface SomeRepository extends BaseJpaRepository<SomeEntity, Long> {

//	@Query("select e from SomeEntity e where e.value in :values")
//	List<SomeEntity> findByValueIn(List<Long> values);

  List<SomeEntity> findBySomeValueIn(List<Long> values);

  List<SomeEntity> findBySomeValueIn(Set<Long> values);
}
