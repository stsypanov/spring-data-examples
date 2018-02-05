package com.luxoft.logeek.repository.impl;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.repository.ChildRepositoryCustom;
import com.luxoft.logeek.util.VelocityTemplateParser;

import java.util.List;

public class ChildRepositoryImpl extends BaseDao implements ChildRepositoryCustom {

	@Override
	public List<Child> findUsingTemplate(boolean fetchParent, boolean fetchToys, boolean orderByAge) {
		String query = VelocityTemplateParser
				.template(RepositoryTemplates.BASE_CHILD_TEMPLATE.path)
				.param("fetchParent", fetchParent)
				.param("fetchToys", fetchToys)
				.param("orderByAge", orderByAge)
				.parse();

		return em.createQuery(query, Child.class).getResultList();
	}

	private enum RepositoryTemplates {
		BASE_CHILD_TEMPLATE("BaseChildTemplate.hql.vm");

		private final String path;

		RepositoryTemplates(String path) {
			this.path = "templates/" + path;
		}
	}
}
