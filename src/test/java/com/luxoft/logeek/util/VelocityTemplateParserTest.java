package com.luxoft.logeek.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VelocityTemplateParserTest {
	private static final String query = "select c from Child c";
	
	@Test
	public void parse() {
		String parse = VelocityTemplateParser.template("templates/StatusTemplate.sql.vm").parse();
		assertEquals(query, parse);
	}

}