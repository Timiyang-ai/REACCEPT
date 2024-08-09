	@Test
	public void getBaseUrl() {
		assertEquals("http://crawljax.com", UrlUtils.getBaseUrl("http://crawljax.com/about/"));

		assertEquals("https://crawljax.com", UrlUtils.getBaseUrl("https://crawljax.com/about/"));

		assertEquals("http://crawljax.com",
				UrlUtils.getBaseUrl("http://crawljax.com/about/history/"));

		assertEquals("http://crawljax.com", UrlUtils.getBaseUrl("http://crawljax.com/"));

		assertEquals("http://crawljax.com", UrlUtils.getBaseUrl("http://crawljax.com"));

		assertEquals("http://crawls.crawljax.com",
				UrlUtils.getBaseUrl("http://crawls.crawljax.com/demo"));

		assertEquals("http://crawls.crawljax.com",
				UrlUtils.getBaseUrl("http://crawls.crawljax.com"));

	}