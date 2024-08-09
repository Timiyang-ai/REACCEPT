@Test(expected = BrowserConnectionException.class)
	public void testFireEvent() throws CrawljaxException {
		browser.fireEvent(new Eventable(new Identification(How.xpath, "/HTML"), EventType.click));
	}