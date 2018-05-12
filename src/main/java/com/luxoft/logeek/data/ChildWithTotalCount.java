package com.luxoft.logeek.data;

import com.luxoft.logeek.entity.Parent;
import org.springframework.beans.factory.annotation.Value;

public interface ChildWithTotalCount {

	@Value("#{target.id}")
	Long getId();

	@Value("#{target.parent}")
	Parent getParent();

	@Value("#{target.age}")
	short getAge();

	Long getTotalCount();

}
