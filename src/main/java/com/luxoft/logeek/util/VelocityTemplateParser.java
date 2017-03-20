package com.luxoft.logeek.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

public class VelocityTemplateParser {
	private String templatePath;
	private Map<String, Object> params = new HashMap<>();

	static {
		Properties props = new Properties();
		props.put(RuntimeConstants.RESOURCE_LOADER, "classpath");
		props.put("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		Velocity.init(props);
	}

	private VelocityTemplateParser(String templatePath) {
		this.templatePath = templatePath;
	}

	public static VelocityTemplateParser template(String templatePath) {
		Objects.requireNonNull(templatePath);
		return new VelocityTemplateParser(templatePath);
	}

	public VelocityTemplateParser param(String name, Object param) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(param);
		params.put(name, param);
		return this;
	}

	public VelocityTemplateParser param(Map<String, Object> params) {
		Objects.requireNonNull(params);
		this.params.putAll(params);
		return this;
	}

	public String parse() {
		try (StringWriter sw = new StringWriter()) {
			Velocity.mergeTemplate(templatePath, UTF_8.name(), new VelocityContext(params), sw);
			return sw.toString();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
