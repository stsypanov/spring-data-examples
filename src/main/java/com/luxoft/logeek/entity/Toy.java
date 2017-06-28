package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Toy {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "child_id")
	private Child owner;

	public void setOwner(Child child) {
		this.owner = child;
	}
}
