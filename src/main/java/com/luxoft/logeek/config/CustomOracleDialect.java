package com.luxoft.logeek.config;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class CustomOracleDialect extends H2Dialect {

  public CustomOracleDialect() {
    super();
    registerFunction("coalesce", new StandardSQLFunction("coalesce"));
    registerFunction("total_count", new TotalCountFunc());
  }
}
