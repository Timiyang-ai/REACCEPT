@Test
	public void testSupportsFileExtension() {
		assertTrue("Should support \"ac\" extension.",
				analyzer.accept(new File("configure.ac")));
		assertTrue("Should support \"in\" extension.",
				analyzer.accept(new File("configure.in")));
		assertTrue("Should support \"configure\" extension.",
				analyzer.accept(new File("configure")));
	}