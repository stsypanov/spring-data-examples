package com.luxoft.logeek.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Entity
public class Pupil {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private int age;

	protected Pupil() {
	}

	public Pupil(int age) {
		this.age = age;
	}
}
