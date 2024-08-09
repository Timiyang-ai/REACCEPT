@Test
	public final void testFireEvent() throws CrawljaxException, MalformedURLException {
		browser.goToUrl(new URL(SERVER.getSiteUrl() + "simple.html"));
		browser.fireEvent(new Eventable(new Identification(How.xpath, "//H1"), EventType.click));
	}