package com.luxoft.logeek.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Child {

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(name = "parent_id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Parent parent;

	@Column
	private short age;

	@OneToMany(mappedBy = "owner")
	@LazyCollection(value = LazyCollectionOption.EXTRA)
	private List<Toy> toys;

	public Child(Parent parent) {
		this.parent = parent;
	}

	protected Child() {
	}

	public void addToy(Toy toy) {
		if (toys == null) {
			toys = new ArrayList<>();
		}
		toy.setOwner(this);
		toys.add(toy);
	}
}
