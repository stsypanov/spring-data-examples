package com.luxoft.logeek.repository.impl;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.repository.ChildRepositoryCustom;
import com.luxoft.logeek.util.TemplateParser;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.luxoft.logeek.repository.impl.ChildRepositoryImpl.RepositoryTemplates.BASE_CHILD_TEMPLATE;

@RequiredArgsConstructor
public class ChildRepositoryImpl extends BaseDao implements ChildRepositoryCustom {
  private final TemplateParser templateParser;

  @Override
  public List<Child> findUsingTemplate(boolean fetchParent, boolean fetchToys, boolean orderByAge) {
    Map<String, Object> params = new HashMap<>();
    params.put("fetchParent", fetchParent);
    params.put("fetchToys", fetchToys);
    params.put("orderByAge", orderByAge);

    String query = templateParser.parseFreeMarker(BASE_CHILD_TEMPLATE.name, params);

    return em.createQuery(query, Child.class).getResultList();
  }

  @RequiredArgsConstructor
  enum RepositoryTemplates {
    BASE_CHILD_TEMPLATE("BaseChildTemplate.hql.ftl");

    public final String name;
  }
}
