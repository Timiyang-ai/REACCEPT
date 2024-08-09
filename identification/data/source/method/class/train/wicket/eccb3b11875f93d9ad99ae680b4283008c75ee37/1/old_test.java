@Test
	public void writeJavaScriptUrl() throws Exception
	{
		StringResponse response = new StringResponse();
		String url = "some/url;jsessionid=1234?p1=v1&p2=v2";
		String id = "some&bad%id";
		boolean defer = true;
		String charset = "some&bad%%charset";
		JavaScriptUtils.writeJavaScriptUrl(response, url, id, defer, charset);

		assertEquals("<script type=\"text/javascript\" id=\"some&amp;bad%id\" defer=\"defer\" charset=\"some&amp;bad%%charset\" src=\"some/url;jsessionid=1234?p1=v1&amp;p2=v2\"></script>\n", response.toString());
	}