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
        Long count = bankAccountControlRepository.countByUserAccount_Id(1L);

        Long count1 = bankAccountControlRepository.countByUserAccountId(1L);

        assertEquals(count, count1);
    }
}