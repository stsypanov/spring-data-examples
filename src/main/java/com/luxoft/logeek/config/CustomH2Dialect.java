package com.luxoft.logeek.config;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class CustomH2Dialect extends H2Dialect {

    public CustomH2Dialect() {
        super();
        registerFunction("coalesce", new StandardSQLFunction("coalesce"));
        registerFunction("total_count", new TotalCountFunc());
    }
}
