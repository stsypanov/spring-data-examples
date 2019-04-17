package com.luxoft.logeek.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Setter
@Getter
@Entity
public class EntityWithManyStringFields {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
  @SequenceGenerator(name = "SEQUENCE", sequenceName = "PUPIL_SEQ", allocationSize = 5000)
  private Long id;

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

  @Column
  private String field7;

  @Column
  private String field8;

  public EntityWithManyStringFields() {
  }
}
