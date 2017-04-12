package com.luxoft.logeek.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class CompositeKey implements Serializable {
	private Long key1;
	private Long key2;

	public CompositeKey(Long key1, Long key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	protected CompositeKey() {
	}
}
