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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "SOME_SEQ", allocationSize = 1000)
	private Long id;
	@Column
	private Long value;

	protected SomeEntity() {
	}
}