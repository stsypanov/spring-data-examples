package com.luxoft.logeek.entity.listener;

import javax.persistence.PostRemove;
import javax.persistence.PreRemove;

/**
 * Created by Сергей on 03.05.2017.
 */
public class PupilListener {


	@PreRemove
	void preRemove(Object object) {
		throw new RuntimeException();
	}

	@PostRemove
	void postRemove(Object object) {
		throw new RuntimeException();
	}


}
