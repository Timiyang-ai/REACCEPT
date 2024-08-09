	@Test
	public void getDTFJMinorVersion() {
		for (int i=0; i!= ddrTestObjects.size(); i++) {
			ImageFactory ddrFactory = (ImageFactory) ddrTestObjects.get(i);
			ImageFactory jextractFactory = (ImageFactory) jextractTestObjects.get(i);
			
			assertTrue(ddrFactory.getDTFJMinorVersion() >= jextractFactory.getDTFJMinorVersion());
		}
	}