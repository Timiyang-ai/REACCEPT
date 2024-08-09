public static void writeJavaScript(final Response response, final CharSequence text)
	{
		AttributeMap attributes = new AttributeMap();
		attributes.putAttribute(ATTR_TYPE, "text/javascript");
		writeInlineScript(response, text, attributes);
	}