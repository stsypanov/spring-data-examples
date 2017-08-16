package com.luxoft.logeek.repository;

import com.google.common.collect.Lists;
import com.luxoft.logeek.misc.OracleConstants;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;

public class BaseJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

	private JpaEntityInformation<T, ?> entityInformation;
	private EntityManager entityManager;
	private CrudMethodMetadata metadata;

	public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	@Override
	public void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata) {
		this.metadata = crudMethodMetadata;
		super.setRepositoryMethodMetadata(crudMethodMetadata);
	}

	@Override
	public T findOne(ID id, EntityGraph<T> graph) {
		return findOne(id, graph, false);
	}

	@Override
	public T findOne(ID id, String graphName) {
		return findOne(id, graphName, false);
	}

	@Override
	public T findOne(ID id, String graphName, boolean readOnly) {
		Assert.notNull(id, "The given id must not be null!");

		EntityGraph<?> entityGraph = entityManager.getEntityGraph(graphName);

		return this.findOne(id, entityGraph, readOnly);
	}

	@Override
	public T findOne(ID id, EntityGraph graph, boolean readOnly) {
		Map<String, Object> hints = new HashMap<>();
		hints.put(QueryHints.HINT_READONLY, true);
		hints.put(QueryHints.HINT_LOADGRAPH, graph);

		return entityManager.find(getDomainClass(), id, hints);
	}

	@Override
	public List<T> findAll(Iterable<ID> ids, boolean readOnly) {

		if (ids == null || !ids.iterator().hasNext()) {
			return Collections.emptyList();
		}

		if (entityInformation.hasCompositeId()) {

			List<T> results = new ArrayList<T>();

			for (ID id : ids) {
				results.add(findOne(id));
			}

			return results;
		}

		ByIdsSpecification<T> specification = new ByIdsSpecification<T>(entityInformation);
		TypedQuery<T> query = getQuery(specification, (Sort) null).setHint(QueryHints.HINT_READONLY, true);

		return query.setParameter(specification.parameter, ids).getResultList();
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		if (ids instanceof Collection && ((Collection) ids).size() <= OracleConstants.MAX_IN_COUNT) {
			return this.findAll(ids, false);
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(getDomainClass());

		Root<T> from = query.from(getDomainClass());

		Predicate predicate = cb.or(splitToPredicates(ids, from));
		query = query.select(from).where(predicate);

		return entityManager.createQuery(query).getResultList();
	}

	private Predicate[] splitToPredicates(Iterable<ID> ids, Root<T> root) {
		List<List<ID>> chunks = Lists.partition(Lists.newArrayList(ids), OracleConstants.MAX_IN_COUNT);

		return chunks.stream()
				.map(chunk -> root.get(entityInformation.getIdAttribute()).in(chunk))
				.toArray(Predicate[]::new);
	}


	private static final class ByIdsSpecification<T> implements Specification<T> {

		private final JpaEntityInformation<T, ?> entityInformation;

		ParameterExpression<Iterable> parameter;

		public ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
			this.entityInformation = entityInformation;
		}

		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

			Path<?> path = root.get(entityInformation.getIdAttribute());
			parameter = cb.parameter(Iterable.class);
			return path.in(parameter);
		}
	}
}
