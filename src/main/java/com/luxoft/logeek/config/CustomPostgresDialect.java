package com.luxoft.logeek.config;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class CustomPostgresDialect extends PostgreSQL9Dialect {

  public CustomPostgresDialect() {
    super();
    registerFunction("coalesce", new StandardSQLFunction("coalesce"));
    registerFunction("total_count", new TotalCountFunc());
  }
}
