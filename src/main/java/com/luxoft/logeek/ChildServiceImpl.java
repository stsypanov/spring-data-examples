package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.repository.ChildRepository;
import com.luxoft.logeek.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Сергей on 02.04.2017.
 */
@Component
@Transactional
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;

    @Override
    public Child newChildForParent(long parentId) {
        Child child = new Child();
        parentRepository.findById(parentId).ifPresent(child::setParent);

        return childRepository.save(child);
    }

    @Override
    public Child optimizedNewChildForParent(long parentId) {
        Child child = new Child();
        child.setParent(parentRepository.getOne(parentId));

        return childRepository.save(child);
    }
}
