package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.CompositeKey;
import com.luxoft.logeek.entity.EntityWithCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityWithCompositeKeyRepository extends JpaRepository<EntityWithCompositeKey, CompositeKey>{
}
