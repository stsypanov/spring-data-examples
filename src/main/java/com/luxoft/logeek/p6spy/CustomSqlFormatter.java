package com.luxoft.logeek.p6spy;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class CustomSqlFormatter implements MessageFormattingStrategy {

  @Override
  public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
    return
      "elapsed: " + elapsed + " ms\tsql:\t" + P6Util.singleLine(sql);
  }
}
