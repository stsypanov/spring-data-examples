package com.luxoft.logeek.repository.jira729;

import com.luxoft.logeek.entity.jira729.BankAccountControl;
import com.luxoft.logeek.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankAccountControlRepository extends BaseJpaRepository<BankAccountControl, Long> {

    Long countByUserAccount_Id(Long id);

    @Query("select count(c.id) from BankAccountControl c where c.userAccount.id = ?1")
    Long countByUserAccountId(Long id);
}
