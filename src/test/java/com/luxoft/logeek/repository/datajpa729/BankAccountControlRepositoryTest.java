package com.luxoft.logeek.repository.datajpa729;

import com.luxoft.logeek.TestBase;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertEquals;

@Sql("/BankAccountControlRepositoryTest.sql")
public class BankAccountControlRepositoryTest extends TestBase {
    @Autowired
    BankAccountControlRepository bankAccountControlRepository;
    private Long count1;
    private Long count2;

    @Test
    public void countByUserAccountId() {
        count1 = bankAccountControlRepository.countByUserAccountId(1L);
    }

    @Test
    public void countByUserAccount_Id() {
        count2 = bankAccountControlRepository.countByUserAccount_Id(1L);
    }

    @After
    public void tearDown() {
        if (count1 != null && count2 != null) {
            assertEquals(count1, count2);
        }
    }
}