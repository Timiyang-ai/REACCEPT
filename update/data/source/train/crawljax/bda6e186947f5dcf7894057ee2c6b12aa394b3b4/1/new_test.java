@Test
	public final void testFireEvent() throws Exception {
		browser.goToUrl(new URL(SERVER.getSiteUrl() + "simple.html"));
		browser.fireEventAndWait(new Eventable(new Identification(How.xpath, "//H1"),
		        EventType.click));
	}