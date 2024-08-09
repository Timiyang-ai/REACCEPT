@Test
	public void pop_grow() {
		FastQueue<Point2D_I32> alg = new FastQueue<Point2D_I32>(1,Point2D_I32.class,true);

		int before = alg.getMaxSize();
		for( int i = 0; i < 20; i++ ) {
			alg.pop();
		}
		alg.get(19);
		int after = alg.getMaxSize();
		assertTrue(after>before);
	}