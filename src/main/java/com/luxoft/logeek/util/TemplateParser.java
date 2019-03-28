package com.luxoft.logeek.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateParser {
  private final Configuration configuration;

  @SneakyThrows
  public String prepareQuery(String templateName, Map<String, Object> params){
    Template template = configuration.getTemplate(templateName);
    return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
  }
}
