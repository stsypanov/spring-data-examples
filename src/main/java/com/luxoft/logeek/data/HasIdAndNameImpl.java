package com.luxoft.logeek.data;

public class HasIdAndNameImpl implements HasIdAndName {
	
	private final Long id;
	private final String name;

	public HasIdAndNameImpl(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
