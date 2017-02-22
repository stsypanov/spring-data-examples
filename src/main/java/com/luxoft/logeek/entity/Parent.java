package com.luxoft.logeek.entity;

import javax.persistence.*;

@Entity
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	public Parent(String name) {
		this.name = name;
	}

	protected Parent() {
	}
}
