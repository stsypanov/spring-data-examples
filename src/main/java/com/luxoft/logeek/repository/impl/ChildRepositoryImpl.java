package com.luxoft.logeek.repository.impl;

import com.luxoft.logeek.data.BriefChildData;
import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.repository.ChildRepositoryCustom;
import com.luxoft.logeek.util.TemplateParser;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.luxoft.logeek.repository.impl.ChildRepositoryImpl.RepositoryTemplates.BASE_CHILD_TEMPLATE;

@RequiredArgsConstructor
public class ChildRepositoryImpl extends BaseDao implements ChildRepositoryCustom {
  private final TemplateParser templateParser;

  @Override
  public List<Child> findAll(boolean fetchParent, boolean fetchToys, boolean orderByAge) {
    Map<String, Object> params = new HashMap<>();
    params.put("fetchParent", fetchParent);
    params.put("fetchToys", fetchToys);
    params.put("orderByAge", orderByAge);

    String query = templateParser.prepareQuery(BASE_CHILD_TEMPLATE.name, params);

    return em.createQuery(query, Child.class).getResultList();
  }

  @Override
  public Page<BriefChildData> browseWithTotalCount(Pageable pageable) {
    String query =
      "select " +
        " c.id as id," +
        " c.age as age, " +
        " total_count(c.id) as totalCount" +
        " from Child c " +
        "join c.parent p " +
        "where p.name = 'папа'";

    List<BriefChildData> list = em.unwrap(Session.class)
      .createQuery(query)
      .setFirstResult((int) pageable.getOffset())
      .setMaxResults(pageable.getPageSize())
      .setResultTransformer(Transformers.aliasToBean(BriefChildData.class))
      .getResultList();

    if (list.isEmpty()) {
      return new PageImpl<>(Collections.emptyList());
    }

    long totalCount = list.get(0).getTotalCount();

    return new PageImpl<>(list, pageable, totalCount);
  }

  @RequiredArgsConstructor
  enum RepositoryTemplates {
    BASE_CHILD_TEMPLATE("BaseChildTemplate.hql.ftl");

    public final String name;
  }
}
