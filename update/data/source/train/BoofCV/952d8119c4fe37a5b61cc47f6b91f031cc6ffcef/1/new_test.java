@Test
	public void saveOriginalReference() {
		Dummy pyramid = new Dummy(ImageUInt8.class,false);
		pyramid.setScaleFactors(1,2,4);
		pyramid.initialize(100,120);

		assertTrue(pyramid.getLayer(0) != null);

		pyramid = new Dummy(ImageUInt8.class,true);
		pyramid.setScaleFactors(1,2,4);
		pyramid.initialize(100,120);

		assertTrue(pyramid.getLayer(0) == null);

		// first layer is not 1 so the flag should be ignored
		pyramid = new Dummy(ImageUInt8.class,true);
		pyramid.setScaleFactors(2,4);
		pyramid.initialize(100,120);

		assertTrue(pyramid.getLayer(0) != null);
	}