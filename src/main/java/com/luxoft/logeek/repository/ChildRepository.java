package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings({"SameParameterValue"})
public interface ChildRepository extends JpaRepository<Child, Long>, ChildRepositoryCustom {

	List<Child> findByParentName(String name);

	@Query("select c from Child c " +
			" join c.parent parent " +
			"where parent.name = :name")
	List<Child> findByParentNameWithExplicitJoin(@Param("name") String name);

	@Query("select c from Child c " +
			"where c.parent.name = :name")
	List<Child> findByParentNameNoExplicitJoin(@Param("name") String name);

	List<Child> findByParentId(Long id);

	@Query("select c from Child c " +
			" join c.parent parent " +
			"where parent.id = :id")
	List<Child> findByParentIdWithExplicitJoin(@Param("id") Long id);

	@Query("select c from Child c " +
			"where c.parent.id = :id")
	List<Child> findByParentIdWithoutExplicitJoin(@Param("id") Long id);
}
