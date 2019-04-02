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
public class DailyRecord {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String currency;

  @Column(name = "record_rate")
  private BigDecimal rate;

  @Column(name = "fixed_rate")
  private BigDecimal fxRate;

  @Setter(value = AccessLevel.PRIVATE)
  @Formula("select avg(r.record_rate) from daily_record r where r.currency = currency")
  private BigDecimal avgRate;
}
