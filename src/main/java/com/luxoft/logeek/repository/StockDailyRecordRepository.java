package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.StockDailyRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

@SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
public interface StockDailyRecordRepository extends BaseJpaRepository<StockDailyRecord, Long> {

    @Query("select coalesce(record.fixedRate, record.averageRecordRate) " +
            " from StockDailyRecord record " +
            "where record.currency = :currency")
    BigDecimal findRateByCurrency(@Param("currency") String currency);


    /*@Query("select coalesce(record.fixedRate, record.recordRate, record.averageRecordRate) " +
            " from StockDailyRecord record " +
            "where record.currency = :currency")
    BigDecimal findRateByCurrency(@Param("currency") String currency);*/
}
