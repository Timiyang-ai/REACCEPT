@Test
	void writeLink() throws Exception
	{
		StringResponse response = new StringResponse();
		String url = "some/url;jsessionid=1234?with=parameters&p1=v1";
		// Media may contain funny things, and it's OK
		String media = "some&bad&media (min-height: 680px), screen and (orientation: portrait)";
		AttributeMap attributes = new AttributeMap();
		attributes.putAttribute(CssUtils.ATTR_LINK_REL, "stylesheet");
		attributes.putAttribute(CssUtils.ATTR_TYPE, "text/css");
		attributes.putAttribute(CssUtils.ATTR_LINK_HREF, url);
		attributes.putAttribute(CssUtils.ATTR_LINK_MEDIA, media);
		attributes.putAttribute(CssUtils.ATTR_ID, "markupId");
		CssUtils.writeLink(response, attributes);
		assertEquals("<link rel=\"stylesheet\" type=\"text/css\" href=\"some/url;jsessionid=1234?with=parameters&amp;p1=v1\" media=\"some&amp;bad&amp;media (min-height: 680px), screen and (orientation: portrait)\" id=\"markupId\" />", response.toString());
	}