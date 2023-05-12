package com.luxoft.logeek.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "entity_with_composite_key")
public class EntityWithCompositeKey {
  @EmbeddedId
  private CompositeKey key;
}
