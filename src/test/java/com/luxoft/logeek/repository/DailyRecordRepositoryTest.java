package com.luxoft.logeek.repository;

import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.DailyRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@ActiveProfiles(value = "oracle", inheritProfiles = false)
@Sql("/DailyRecordRepositoryTest.sql")
public class DailyRecordRepositoryTest extends TestBase {
  @Autowired
  private DailyRecordRepository dailyRecordRepository;

  @Test
  public void findById() {
    DailyRecord record = dailyRecordRepository.findById(1L).orElseThrow(NullPointerException::new);
    BigDecimal expected = BigDecimal.valueOf(2);

    assertEquals(0, record.getAvgRate().compareTo(expected));
  }

  @Test
  public void findRateByCurrency() {
    dailyRecordRepository.findRateByCurrency("USD");
  }
}