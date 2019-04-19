package com.luxoft.logeek.repository;

import com.google.common.collect.Lists;
import com.luxoft.logeek.misc.OracleConstants;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BaseJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

  private final JpaEntityInformation<T, ?> entityInfo;
  private final EntityManager entityManager;

  public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInfo, EntityManager entityManager) {
    super(entityInfo, entityManager);
    this.entityInfo = entityInfo;
    this.entityManager = entityManager;
  }

  @Override
  public T findOne(ID id, String graphName) {
    Assert.notNull(id, "The given id must not be null!");

    EntityGraph<?> graph = entityManager.getEntityGraph(graphName);

    Map<String, Object> hints = Collections.singletonMap(QueryHints.HINT_LOADGRAPH, graph);

    return entityManager.find(getDomainClass(), id, hints);
  }

  @Override
  public T findOne(ID id, boolean readOnly) {
    Assert.notNull(id, "The given id must not be null!");

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = criteriaBuilder.createQuery(getDomainClass());

    Root<T> from = query.from(getDomainClass());
    Predicate predicate = criteriaBuilder.equal(from.get(entityInfo.getIdAttribute()), id);

    query = query.select(from).where(predicate);

    return entityManager.createQuery(query)
      .setHint(QueryHints.HINT_READONLY, readOnly)
      .getSingleResult();
  }

  @Override
  public T findOneStateless(ID id) {
    Assert.notNull(id, "The given id must not be null!");

    try (StatelessSession statelessSession = entityManager.unwrap(Session.class).getSessionFactory().openStatelessSession()) {
      Class<T> domainClass = getDomainClass();
      return domainClass.cast(statelessSession.get(domainClass, id));
    }
  }

  @Override
  public List<T> findAllById(Iterable<ID> ids) {
    Assert.notNull(ids, "The given Iterable of Id's must not be null!");

    ArrayList<ID> idsCopy = Lists.newArrayList(ids);

    if (idsCopy.size() <= OracleConstants.MAX_IN_COUNT) {
      return super.findAllById(ids);
    }

    return findAll(idsCopy);
  }

  private List<T> findAll(ArrayList<ID> ids) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = cb.createQuery(getDomainClass());

    Root<T> from = query.from(getDomainClass());

    Predicate predicate = toPredicate(cb, ids, from);
    query = query.select(from).where(predicate);

    return entityManager.createQuery(query).getResultList();
  }

  private Predicate toPredicate(CriteriaBuilder cb, ArrayList<ID> ids, Root<T> root) {
    List<List<ID>> chunks = Lists.partition(ids, OracleConstants.MAX_IN_COUNT);

    SingularAttribute<? super T, ?> id = entityInfo.getIdAttribute();

    Predicate[] predicates = chunks.stream()
      .map(chunk -> root.get(id).in(chunk))
      .toArray(Predicate[]::new);
    return cb.or(predicates);
  }

}
