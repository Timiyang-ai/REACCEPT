@Test
	public final void testGoToUrl() throws CrawljaxException, MalformedURLException {
		// TODO Stefan; bug in WebDriver iff you specify bla:// will end up in NullPointer.
		browser.goToUrl(new URL("http://non.exsisting.domain"));
	}