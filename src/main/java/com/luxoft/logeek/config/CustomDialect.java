package com.luxoft.logeek.config;

import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class CustomDialect extends Oracle12cDialect {

    public CustomDialect() {
        super();
        registerFunction("coalesce", new StandardSQLFunction("coalesce"));
    }
}
