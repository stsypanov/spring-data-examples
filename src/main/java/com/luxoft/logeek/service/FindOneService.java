package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.SomeEntity;

public interface FindOneService {

    SomeEntity findOne(Long id, boolean readOnly);

    SomeEntity findOne(Long id);

    SomeEntity save(SomeEntity entity);
}
