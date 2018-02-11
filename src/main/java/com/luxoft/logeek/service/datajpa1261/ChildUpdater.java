package com.luxoft.logeek.service.datajpa1261;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import com.luxoft.logeek.entity.Toy;
import com.luxoft.logeek.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChildUpdater {
    private final ChildRepository repository;

    @Transactional
    public Child updateChild(Long childId, short age, Parent parent) {
        Child child = repository.findOne(childId);
        child.setAge(age);
        child.setParent(parent);
        child.addToy(new Toy());
        return repository.save(child);
    }

    /**
     * Without repository.save()
     * @see <a href="https://jira.spring.io/browse/DATAJPA-1261">https://jira.spring.io/browse/DATAJPA-1261</a>
     */
    @Transactional
    public Child updateChildIncorrectly(Long childId, short age, Parent parent) {
        Child child = repository.findOne(childId);
        child.setAge(age);
        child.setParent(parent);
        child.addToy(new Toy());
        return child;
    }
}
