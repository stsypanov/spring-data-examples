package com.luxoft.logeek.entity;

import javax.persistence.*;

@Entity
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "PARENT_SEQ", allocationSize = 1000)
	private Long id;

	@Column
	private String name;

	public Parent(String name) {
		this.name = name;
	}

	protected Parent() {
	}
}
