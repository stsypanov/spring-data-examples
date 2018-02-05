package com.luxoft.logeek.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.TestBase;
import com.luxoft.logeek.entity.StockDailyRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@ActiveProfiles(value = "oracle", inheritProfiles = false)
@DatabaseSetup("/StockDailyRecordRepositoryTest.xml")
public class StockDailyRecordRepositoryTest extends TestBase {
    @Autowired
    private StockDailyRecordRepository stockDailyRecordRepository;

    @Test
    public void findRateByCurrency() {
        StockDailyRecord record = stockDailyRecordRepository.findOne(1L);
        BigDecimal expected = BigDecimal.valueOf(2);

        assertEquals(0, record.getAverageFixedRate().compareTo(expected));
    }

}