package com.luxoft.logeek.data;

import com.luxoft.logeek.entity.Child;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChildData {
  private final Child child;
  private final long count;
}
