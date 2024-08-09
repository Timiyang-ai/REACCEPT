@Test
	public void isInitialized() {
		Dummy pyramid = new Dummy(ImageUInt8.class,false);
		assertFalse(pyramid.isInitialized());
		pyramid.setScaleFactors(1,2,4);
		pyramid.initialize(100,120);
		assertTrue(pyramid.isInitialized());
	}