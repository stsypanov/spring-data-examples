package com.luxoft.logeek.util;

import com.luxoft.logeek.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class TemplateParserTest extends TestBase {
  @Autowired
  private TemplateParser templateParser;

  @Test
  public void parseFreeMarker() {
    Map<String, Object> params = new HashMap<>();
    params.put("fetchParent", true);
    params.put("fetchToys", true);
    params.put("orderByAge", true);

    templateParser.prepareQuery("BaseChildTemplate.hql.ftl", params);
  }
}