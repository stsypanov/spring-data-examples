package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Сергей on 02.04.2017.
 */
@SuppressWarnings("unused")
@Component
@Transactional
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {
	private final ChildRepository childRepository;

}
