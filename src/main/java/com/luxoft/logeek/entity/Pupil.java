package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Pupil {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "PUPIL_SEQ", allocationSize = 5000)
	private Long id;

	@Column
	private int age;
	
	@Column
	private String name;

	protected Pupil() {
	}

	public Pupil(int age) {
		this.age = age;
	}

	public Pupil(int age, String name) {
		this.age = age;
		this.name = name;
	}
}
