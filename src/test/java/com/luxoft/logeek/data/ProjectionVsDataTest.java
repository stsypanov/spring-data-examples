package com.luxoft.logeek.data;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.Parent;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles(value = "postgres", inheritProfiles = false)
public class ProjectionVsDataTest extends TestBase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void getChildData() {
		List<ChildData> allWithParents = childRepository.findAllWithTotalCountAsData();

		allWithParents.forEach(ChildData::getChild);
	}

	@Test
	public void findAllWithTotalCount() throws Exception {
		List<ChildWithTotalCount> allWithTotalCount = childRepository.findAllWithTotalCountAsProjection();
		
		Long totalCount = allWithTotalCount.get(0).getTotalCount();
		Parent parent = allWithTotalCount.get(0).getParent();
	}

}
