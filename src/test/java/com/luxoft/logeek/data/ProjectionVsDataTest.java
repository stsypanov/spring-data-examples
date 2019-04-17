package com.luxoft.logeek.data;

import com.luxoft.logeek.TestBase;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Sql("/ProjectionVsDataTest.sql")
@ActiveProfiles(value = "oracle", inheritProfiles = false)
public class ProjectionVsDataTest extends TestBase {

  @Test
  public void getChildData() {
    List<ChildData> allWithParents = childRepository.findAllWithTotalCountAsData();

    long count = allWithParents.get(0).getCount();

    assertThat(count, is(5L));
  }

  @Test
  public void findAllWithTotalCount() {
    List<ChildWithTotalCount> allWithTotalCount = childRepository.findAllWithTotalCountAsProjection();

    long totalCount = allWithTotalCount.get(0).getTotalCount();

    assertThat(totalCount, is(5L));
  }

}
