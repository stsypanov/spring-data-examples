package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.repository.ChildRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Сергей on 02.04.2017.
 */
@SuppressWarnings("unused")
@Component
@Transactional
public class ChildServiceImpl implements ChildService {
	private final ChildRepository childRepository;
	private final EntityManager em;

	public ChildServiceImpl(
			ChildRepository childRepository,
			EntityManager em
	) {
		this.em = em;
		this.childRepository = childRepository;
	}

	@Override
	public void saveChild(Child child) {
		childRepository.save(child);
	}
	
	@Override
	@Transactional
	public void saveWithEm(Child child) {
		em.persist(child);
		em.flush();
	}

	@Override
	public List findAllWithEm() {
		return em.createQuery("select c from Child c").getResultList();
	}


	public List<Child> findAllByParentIds(List<Long> ids) {
		Set<Long> parentIds = new HashSet<>(ids);
		return childRepository.findAllByParentIds(parentIds);
	}
}
