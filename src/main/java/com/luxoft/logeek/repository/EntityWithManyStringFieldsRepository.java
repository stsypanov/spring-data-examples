package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.EntityWithManyStringFields;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityWithManyStringFieldsRepository extends JpaRepository<EntityWithManyStringFields, Long> {
}
