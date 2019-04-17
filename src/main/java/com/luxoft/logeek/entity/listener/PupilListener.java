package com.luxoft.logeek.entity.listener;

import com.luxoft.logeek.entity.Pupil;

import javax.persistence.PreRemove;

/**
 * Created by Сергей on 03.05.2017.
 */
public class PupilListener {

  public static final String MESSAGE =
    "It's prohibited to delete pupils older than 9.";

  @PreRemove
  void preRemove(Pupil pupil) {
    int age = pupil.getAge();
    if (age > 9) {
      throw new RuntimeException(MESSAGE);
    }
  }

}
