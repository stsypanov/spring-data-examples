package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.repository.ChildRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Сергей on 02.04.2017.
 */
@Component
@Transactional
public class ChildServiceImpl implements ChildService {
	private final ChildRepository childRepository;

	public ChildServiceImpl(ChildRepository childRepository) {
		this.childRepository = childRepository;
	}

	@Override
	public void saveChild(Child child) {
		childRepository.save(child);
	}
}
