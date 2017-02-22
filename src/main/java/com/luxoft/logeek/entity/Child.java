package com.luxoft.logeek.entity;


import javax.persistence.*;

@Entity
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private Parent parent;

	public Child(Parent parent) {
		this.parent = parent;
	}

	protected Child() {
	}
}
