package com.luxoft.logeek.entity.another;

import com.luxoft.logeek.entity.CompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(value = CompositeKey.class)
public class EntityWithCompositeKey {

	@Id
	private Long key1;
	@Id
	private Long key2;

	public EntityWithCompositeKey(Long key1, Long key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	protected EntityWithCompositeKey() {
	}
}
