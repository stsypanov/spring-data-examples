package com.luxoft.logeek.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BriefChildData {
  private Long id;
  private short age;
  private long totalCount;

  public BriefChildData(Long id, short age) {
    this.id = id;
    this.age = age;
  }
}
