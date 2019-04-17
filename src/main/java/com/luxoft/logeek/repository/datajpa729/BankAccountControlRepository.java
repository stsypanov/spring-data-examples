package com.luxoft.logeek.repository.datajpa729;

import com.luxoft.logeek.entity.jira729.BankAccountControl;
import com.luxoft.logeek.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountControlRepository extends BaseJpaRepository<BankAccountControl, Long> {

  /**
   * Bug: unnecessary left outer join
   *
   * @see <a href="https://jira.spring.io/browse/DATAJPA-729">https://jira.spring.io/browse/DATAJPA-729</a>
   */
  long countByUserAccount_Id(Long id);

  @Query("select count(c.id) from BankAccountControl c " +
    " where c.userAccount.id = :id")
  long countByUserAccountId(@Param("id") Long id);
}
