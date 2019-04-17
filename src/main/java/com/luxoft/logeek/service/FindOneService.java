package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.SomeEntity;

import java.util.Optional;

public interface FindOneService {

  SomeEntity findOne(Long id, boolean readOnly);

  Optional<SomeEntity> findOne(Long id);

  SomeEntity save(SomeEntity entity);
}
