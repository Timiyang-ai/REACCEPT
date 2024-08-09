@Test
	public void process() {
		TldRegionTracker alg = createAlg();

		Rectangle2D_F64 rect = new Rectangle2D_F64(10,20,115,125);

		alg.initialize(pyramid);
		assertTrue(alg.process(pyramid, rect));
		assertEquals(alg.getPairs().size,10*10);
		assertTrue(alg.process(pyramid, rect));
		assertEquals(alg.getPairs().size,10*10);
	}