@Test(expected = BrowserConnectionException.class)
	public void testGetDom() throws CrawljaxException {
		browser.getStrippedDom();
	}