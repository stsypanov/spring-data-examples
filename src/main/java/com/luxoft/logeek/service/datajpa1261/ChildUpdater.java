package com.luxoft.logeek.service.datajpa1261;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import com.luxoft.logeek.entity.Toy;
import com.luxoft.logeek.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChildUpdater {
  private final ChildRepository repository;

  @Transactional
  public Optional<Child> updateChild(Long childId, short age, Parent parent) {
    Optional<Child> child = repository.findById(childId);
    child.ifPresent(c -> c.setAge(age));
    child.ifPresent(c -> c.setParent(parent));
    child.ifPresent(c -> c.addToy(new Toy()));
    return child.map(repository::save);
  }

  /**
   * Without repository.save()
   *
   * @see <a href="https://jira.spring.io/browse/DATAJPA-1261">https://jira.spring.io/browse/DATAJPA-1261</a>
   */
  @Transactional
  public Optional<Child> updateChildIncorrectly(Long childId, short age, Parent parent) {
    Optional<Child> child = repository.findById(childId);
    child.ifPresent(c -> c.setAge(age));
    child.ifPresent(c -> c.setParent(parent));
    child.ifPresent(c -> c.addToy(new Toy()));
    return child;
  }
}
