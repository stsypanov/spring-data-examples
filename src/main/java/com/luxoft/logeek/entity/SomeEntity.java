package com.luxoft.logeek.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class SomeEntity {
	@Id
	private Long id;
	@Column
	private Long value;

	protected SomeEntity() {
	}
}