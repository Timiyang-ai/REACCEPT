@Test(expected = BrowserConnectionException.class)
	public void testFireEvent() throws Exception {
		browser.fireEventAndWait(new Eventable(new Identification(How.xpath, "/HTML"),
		        EventType.click));
	}