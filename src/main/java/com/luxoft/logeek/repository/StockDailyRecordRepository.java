package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.StockDailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface StockDailyRecordRepository extends JpaRepository<StockDailyRecord, Long> {

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")

    @Query("select nvl(record.fixedRate, record.averageRecordRate) " +
            " from StockDailyRecord record " +
            "where record.currency = :currency")
    BigDecimal findRateByCurrency(@Param("currency") String currency);


    /*@Query("select coalesce(record.fixedRate, record.recordRate, record.averageRecordRate) " +
            " from StockDailyRecord record " +
            "where record.currency = :currency")
    BigDecimal findRateByCurrency(@Param("currency") String currency);*/
}
