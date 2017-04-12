package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
public class EntityWithManyFields {
	
	@Id
	private Long id;
	
	@Column
	private String extid;

	@Column
	private String name;
	
	@Column
	private BigDecimal amount1;

	@Column
	private BigDecimal amount2;

	@Column
	private BigDecimal amount3;

	@Column
	private BigDecimal amount4;

	@Column
	private BigDecimal amount5;

	@Column
	private BigDecimal amount6;

	@Column
	private BigDecimal amount7;

	@Column
	private BigDecimal amount8;

	public EntityWithManyFields(Long id, String extid, String name) {
		this.id = id;
		this.extid = extid;
		this.name = name;
	}

	public EntityWithManyFields() {
	}
}
