package com.luxoft.logeek.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class StockDailyRecord {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String currency;

    @Column(name = "record_rate")
    private BigDecimal recordRate;

    @Column(name = "fixed_rate")
    private BigDecimal fixedRate;

    @Setter(value = AccessLevel.PACKAGE)
    @Formula("select avg(rec.fixed_rate) from StockDailyRecord rec where rec.currency = currency")
    private BigDecimal averageFixedRate;

    @Setter(value = AccessLevel.PACKAGE)
    @Formula("select avg(rec.fixed_rate) from StockDailyRecord rec where rec.currency = currency")
    private BigDecimal averageRecordRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
