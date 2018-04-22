package com.luxoft.logeek.repository.datajpa729;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@DatabaseSetup("/BankAccountControlRepositoryTest.xml")
public class BankAccountControlRepositoryTest extends TestBase {
    @Autowired
    BankAccountControlRepository bankAccountControlRepository;

    @Test
    public void countByUserAccount_Id() {
        long count1 = bankAccountControlRepository.countByUserAccount_Id(1L);

        long count2 = bankAccountControlRepository.countByUserAccountId(1L);

        assertEquals(count1, count2);
    }
}