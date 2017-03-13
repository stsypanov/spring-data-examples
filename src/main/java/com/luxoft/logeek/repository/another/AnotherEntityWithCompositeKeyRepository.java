package com.luxoft.logeek.repository.another;

import com.luxoft.logeek.entity.CompositeKey;
import com.luxoft.logeek.entity.another.EntityWithCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotherEntityWithCompositeKeyRepository extends JpaRepository<EntityWithCompositeKey, CompositeKey>{
}
