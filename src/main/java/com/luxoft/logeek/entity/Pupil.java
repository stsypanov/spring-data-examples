package com.luxoft.logeek.entity;

import com.luxoft.logeek.entity.listener.PupilListener;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@EntityListeners({PupilListener.class})
@Entity
@Getter
@Setter
@DynamicUpdate
public class Pupil {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	public int age;
	
	@Column
	private String name;

	@Column
	private String lastName;

	@Column
	private String schoolName;
	
	@Column
	private LocalDate enrolled;

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
