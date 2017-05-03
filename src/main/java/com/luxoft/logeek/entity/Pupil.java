package com.luxoft.logeek.entity;

import com.luxoft.logeek.entity.listener.PupilListener;
import lombok.Getter;

import javax.persistence.*;

@EntityListeners({PupilListener.class})
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
