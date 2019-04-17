package com.luxoft.logeek.config;

import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.util.List;

public class TotalCountFunc implements SQLFunction {

  @Override
  public boolean hasArguments() {
    return true;
  }

  @Override
  public boolean hasParenthesesIfNoArguments() {
    return true;
  }

  @Override
  public Type getReturnType(Type firstArgumentType, Mapping mapping) {
    return StandardBasicTypes.LONG;
  }

  @Override
  public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor factory) {
    if (arguments.size() != 1) {
      throw new IllegalArgumentException("Only 1 argument must be passed into total_count() ");
    }

    return " count(" + arguments.get(0) + ") over () ";
  }

}
