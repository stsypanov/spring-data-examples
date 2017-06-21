package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.EntityWithManyStringFields;
import com.luxoft.logeek.entity.Pupil;

import java.util.List;

public interface ExecutionService {

    void setUp(int size);

    List<EntityWithManyStringFields> execute();

    void tearDown();

}
