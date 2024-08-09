@Test
	public void process() {
		TldRegionTracker alg = createAlg();

		RectangleCorner2D_F64 rect = new RectangleCorner2D_F64(10,20,115,125);

		alg.initialize(pyramid);
		assertTrue(alg.process(pyramid, rect));
		assertEquals(alg.getPairs().size,10*10);
		assertTrue(alg.process(pyramid, rect));
		assertEquals(alg.getPairs().size,10*10);
	}