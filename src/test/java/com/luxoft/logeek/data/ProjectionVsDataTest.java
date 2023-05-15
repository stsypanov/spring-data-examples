package com.luxoft.logeek.data;

import com.luxoft.logeek.TestBase;
import org.h2.engine.Mode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Sql("/ProjectionVsDataTest.sql")
@ActiveProfiles(value = "oracle", inheritProfiles = false)
class ProjectionVsDataTest extends TestBase {

  @BeforeEach
  void setUp() {
    Mode.getInstance("ORACLE").limit = true;
  }

  @Test
  void getChildData() {
    List<ChildData> allWithParents = childRepository.findAllWithTotalCountAsData();

    long count = allWithParents.get(0).getCount();

    assertThat(count, is(5L));
  }

  @Test
  void findAllWithTotalCount() {
    List<ChildWithTotalCount> allWithTotalCount = childRepository.findAllWithTotalCountAsProjection();

    long totalCount = allWithTotalCount.get(0).getCount();

    assertThat(totalCount, is(5L));
  }

  @Test
  void browse() {
    PageRequest request = PageRequest.of(0, 3);

    Page<BriefChildData> page = childRepository.browse(request);

    assertThat(page.getSize(), is(3));
    assertThat(page.getTotalElements(), is(5L));
  }

  @Test
  void browseWithTotalCount() {
    PageRequest request = PageRequest.of(0, 3);

    Page<BriefChildData> page = childRepository.browseWithTotalCount(request);

    assertThat(page.getSize(), is(3));
    assertThat(page.getTotalElements(), is(5L));
  }
}
