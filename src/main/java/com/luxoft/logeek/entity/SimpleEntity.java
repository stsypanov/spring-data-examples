package com.luxoft.logeek.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleEntity {
  @Id
  private int id;

  @Column
  private String name;

  @Column
  private String field1;

  @Column
  private String field2;

  @Column
  private String field3;

  @Column
  private String field4;

  @Column
  private String field5;

  @Column
  private String field6;

}
