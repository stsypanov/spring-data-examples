package com.luxoft.logeek.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EntityWithCompositeKey {

	@EmbeddedId
	private CompositeKey key;

	public EntityWithCompositeKey(CompositeKey key) {
		this.key = key;
	}

	protected EntityWithCompositeKey() {
	}
}
