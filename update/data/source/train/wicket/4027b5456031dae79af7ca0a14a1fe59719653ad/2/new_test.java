@Test
	public void writeJavaScript() throws Exception
	{
		AttributeMap attributes = new AttributeMap();
		attributes.putAttribute(JavaScriptUtils.ATTR_TYPE, "text/javascript");
		attributes.putAttribute(JavaScriptUtils.ATTR_ID, "some\"funny<id&%");
		attributes.putAttribute(JavaScriptUtils.ATTR_SCRIPT_DEFER, true);
		attributes.putAttribute("charset", "some\"funny<charset&%");
		attributes.putAttribute(JavaScriptUtils.ATTR_SCRIPT_SRC, "some/url;jsessionid=1234?p1=v1&p2=v2");
		StringResponse response = new StringResponse();
		JavaScriptUtils.writeScript(response, attributes);

		assertEquals(
				"<script type=\"text/javascript\" id=\"some&quot;funny&lt;id&amp;%\" defer=\"defer\" charset=\"some&quot;funny&lt;charset&amp;%\" src=\"some/url;jsessionid=1234?p1=v1&amp;p2=v2\"></script>\n",
			response.toString());
	}