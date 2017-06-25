package com.luxoft.logeek.entity;

import javax.persistence.*;

@Entity
public class Parent {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	public Parent(String name) {
		this.name = name;
	}

	protected Parent() {
	}
}
