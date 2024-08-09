@Test
	void writeJavaScript()
	{
		StringResponse response = new StringResponse();
		JavaScriptUtils.writeJavaScript(response,
			"var message = 'Scripts are written to the <script></script> tag'");

		assertEquals("<script type=\"text/javascript\" >\n" //
			+ "/*<![CDATA[*/\n" //
			+ "var message = 'Scripts are written to the <script><\\/script> tag'\n" //
			+ "/*]]>*/\n"//
			+ "</script>\n", response.toString());
	}