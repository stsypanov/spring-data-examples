package com.luxoft.logeek.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeKey implements Serializable {
	private long key1;
	private long key2;

	public CompositeKey(long key1, long key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	protected CompositeKey() {
	}
}
