package com.luxoft.logeek.repository;

import com.luxoft.logeek.data.BriefChildData;
import com.luxoft.logeek.entity.Child;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChildRepositoryCustom {

  List<Child> findAll(boolean fetchParent, boolean fetchToys, boolean orderByAge);

  Page<BriefChildData> browseWithTotalCount(Pageable pageable);
}
