@Test
	public final void testGoToUrl() throws CrawljaxException, MalformedURLException {
		// TODO Stefan; bug in WebDriver iff you specify bla:// will end up in NullPointer.
		browser.goToUrl(URI.create("http://non.exsisting.domain"));
	}