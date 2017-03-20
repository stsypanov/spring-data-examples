package com.luxoft.logeek.repository.impl;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.repository.ChildRepositoryCustom;
import com.luxoft.logeek.util.VelocityTemplateParser;

import java.util.List;

public class ChildRepositoryImpl extends BaseDao implements ChildRepositoryCustom {
	
	@Override
	public List<Child> findUsingTemplate() {
		String query = VelocityTemplateParser
				.template(ChildRepositoryTemplate.STATUS.path)
				.parse();
		
		return em.createQuery(query, Child.class).getResultList();
	}
	
	private enum ChildRepositoryTemplate {
		STATUS("StatusTemplate.sql.vm");

		private final String path;

		ChildRepositoryTemplate(String path) {
			this.path = "templates/" + path;
		}
	}
}
