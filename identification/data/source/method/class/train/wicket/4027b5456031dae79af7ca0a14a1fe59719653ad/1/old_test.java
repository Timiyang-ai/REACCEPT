@Test
	void writeLinkUrl() throws Exception
	{
		StringResponse response = new StringResponse();
		String url = "some/url;jsessionid=1234?with=parameters&p1=v1";
		String media = "some&bad&media";
		CssUtils.writeLinkUrl(response, url, media, "markupId");

		assertEquals("<link rel=\"stylesheet\" type=\"text/css\" href=\"some/url;jsessionid=1234?with=parameters&amp;p1=v1\" media=\"some&amp;bad&amp;media\" id=\"markupId\" />", response.toString());
	}