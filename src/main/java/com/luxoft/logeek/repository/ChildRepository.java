package com.luxoft.logeek.repository;

import com.luxoft.logeek.data.BriefChildData;
import com.luxoft.logeek.data.ChildData;
import com.luxoft.logeek.data.ChildWithTotalCount;
import com.luxoft.logeek.entity.Child;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings({"SameParameterValue", "unused"})
public interface ChildRepository extends BaseJpaRepository<Child, Long>, ChildRepositoryCustom {

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


  @Query("select child from Child child " +
    " join child.parent parent " +
    "where parent.id in :ids")
  List<Child> findAllByParentIds(@Param("ids") Iterable<Long> ids);


  @Query("select new com.luxoft.logeek.data.ChildData(" +
    "c, " +
    "total_count(c.id) " +
    ") from Child c")
  List<ChildData> findAllWithTotalCountAsData();

  @Query("select new com.luxoft.logeek.data.BriefChildData(" +
    "c.id, " +
    "c.age " +
    ") from Child c " +
    " join c.parent p " +
    "where p.name = 'папа'")
  Page<BriefChildData> browse(Pageable pageable);

  @Query("select " +
    "c.id as id," +
    "c.parent as parent, " +
    "c.age as age, " +
    "total_count(c.id) as count " +
    "from Child c")
  List<ChildWithTotalCount> findAllWithTotalCountAsProjection();
}
