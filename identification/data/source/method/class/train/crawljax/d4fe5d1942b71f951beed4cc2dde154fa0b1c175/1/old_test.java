@Test
	public final void testGoToUrl() throws CrawljaxException {
		// TODO Stefan; bug in WebDriver iff you specify bla:// will end up in NullPointer.
		browser.goToUrl("http://non.exsisting.domain");
	}