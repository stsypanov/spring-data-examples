package com.luxoft.logeek.repository;

import com.luxoft.logeek.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@ActiveProfiles("oracle")
public class StockDailyRecordRepositoryTest extends TestBase{
    @Autowired private StockDailyRecordRepository stockDailyRecordRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findRateByCurrency() throws Exception {
        BigDecimal rub = stockDailyRecordRepository.findRateByCurrency("RUB");

    }

}