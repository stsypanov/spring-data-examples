package com.luxoft.logeek.p6spy;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class CustomSqlFormatter implements MessageFormattingStrategy {

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
		return new StringBuilder("elapsed: ")
				.append(elapsed)
				.append(" ms;\nprepared:\n")
				.append(P6Util.singleLine(prepared))
				.append("\nsql:\n")
				.append(P6Util.singleLine(sql))
				.append("\ncategory: ")
				.append(category)
				.append("\n")
				.toString();
	}
}
