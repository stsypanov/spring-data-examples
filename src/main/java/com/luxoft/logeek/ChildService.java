package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;

import java.util.List;

/**
 * Created by Сергей on 02.04.2017.
 */
public interface ChildService {

	void saveChild(Child child);

	void saveWithEm(Child child);

	List findAllWithEm();
}
