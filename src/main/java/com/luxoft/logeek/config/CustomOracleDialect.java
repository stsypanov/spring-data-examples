package com.luxoft.logeek.config;

import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class CustomOracleDialect extends Oracle12cDialect {

  public CustomOracleDialect() {
    super();
    registerFunction("coalesce", new StandardSQLFunction("coalesce"));
    registerFunction("total_count", new TotalCountFunc());
  }
}
