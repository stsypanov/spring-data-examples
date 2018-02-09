package com.luxoft.logeek.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NamedEntityGraphs(value = {
		@NamedEntityGraph(name = Child.PARENT, attributeNodes = {
				@NamedAttributeNode("parent")
		})
})
public class Child implements IChild {
	public static final String PARENT = "Child[parent]";

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

	public Child() {
	}

	public void addToy(Toy toy) {
		if (toys == null) {
			toys = new ArrayList<>();
		}
		toy.setOwner(this);
		toys.add(toy);
	}
}
