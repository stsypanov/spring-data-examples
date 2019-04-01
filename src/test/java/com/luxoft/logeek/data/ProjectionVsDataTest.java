package com.luxoft.logeek.data;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.Parent;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@Sql("/ProjectionVsDataTest.sql")
@ActiveProfiles(value = "h2", inheritProfiles = false)
public class ProjectionVsDataTest extends TestBase {

	@Test
	public void getChildData() {
		List<ChildData> allWithParents = childRepository.findAllWithTotalCountAsData();

		allWithParents.forEach(ChildData::getChild);
	}

	@Test
	public void findAllWithTotalCount() {
		List<ChildWithTotalCount> allWithTotalCount = childRepository.findAllWithTotalCountAsProjection();
		
		Long totalCount = allWithTotalCount.get(0).getTotalCount();
		Parent parent = allWithTotalCount.get(0).getParent();
	}

}
