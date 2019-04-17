package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeRepository;
import com.luxoft.logeek.service.FindOneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindOneServiceImpl implements FindOneService {
  private final SomeRepository repository;

  @Override
  @Transactional
  public SomeEntity findOne(Long id, boolean readOnly) {
    return repository.findOne(id, readOnly);
  }

  @Override
  @Transactional
  public Optional<SomeEntity> findOne(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public SomeEntity save(SomeEntity entity) {
    return repository.save(entity);
  }
}
