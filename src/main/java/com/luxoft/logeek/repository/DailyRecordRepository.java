package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.DailyRecord;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

@SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
public interface DailyRecordRepository extends BaseJpaRepository<DailyRecord, Long> {

  @Query("""
          select coalesce(record.fxRate, record.avgRate)
          from DailyRecord record
          where record.currency = :currency
         """)
  BigDecimal findRateByCurrency(String currency);


    /*@Query("select coalesce(record.fixedRate, record.recordRate, record.averageRecordRate) " +
            " from StockDailyRecord record " +
            "where record.currency = :currency")
    BigDecimal findRateByCurrency(String currency);*/
}
