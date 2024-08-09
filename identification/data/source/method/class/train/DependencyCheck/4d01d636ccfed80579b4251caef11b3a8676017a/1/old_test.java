@Test
	public void testSupportsExtension() {
		assertTrue("Should support \"ac\" extension.",
				analyzer.supportsExtension("ac"));
		assertTrue("Should support \"in\" extension.",
				analyzer.supportsExtension("in"));
		assertTrue("Should support \"configure\" extension.",
				analyzer.supportsExtension("configure"));
	}