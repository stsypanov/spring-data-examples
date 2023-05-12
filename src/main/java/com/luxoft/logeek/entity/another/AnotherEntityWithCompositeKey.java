package com.luxoft.logeek.entity.another;

import com.luxoft.logeek.entity.CompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "entity_with_composite_key")
@IdClass(value = CompositeKey.class)
public class AnotherEntityWithCompositeKey {
  @Id
  private Long key1;
  @Id
  private Long key2;
}
