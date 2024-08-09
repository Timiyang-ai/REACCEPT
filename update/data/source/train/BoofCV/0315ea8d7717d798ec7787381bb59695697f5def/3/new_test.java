@Test
	public void splitPixels() {
		SplitMergeLineFitLoop alg = new SplitMergeLineFitLoop(0.01, MINIMUM_SPLIT_FRACTION,100);
		alg.contour = new ArrayList<>();
		for( int i = 0; i < 10; i++ )
			alg.contour.add( new Point2D_I32(i,0));
		alg.N = alg.contour.size();
		alg.contour.get(4).y = 6;// set it just above the threshold

		// tests which require splits on recursive calls
		alg.splitPixels(0,3);
		assertEquals(0, alg.splits.size);
		alg.splitPixels(0, 4);
		assertEquals(1, alg.splits.size);
		assertEquals(3,alg.splits.data[0]);

		// will get a hit from its recursive call.
		// gets split on both sides of the impulse because the impulse is so far from all the other lines
		alg.splits.reset();
		alg.splitPixels(0, 9);
		assertEquals(3, alg.splits.size);
		assertEquals(3,alg.splits.data[0]);
		assertEquals(4,alg.splits.data[1]);
		assertEquals(5,alg.splits.data[2]);

		// Test a few edge cases
		alg.splits.reset();
		alg.splitPixels(0,1);
		assertEquals(0,alg.splits.size);
		alg.splitPixels(9,1);
		assertEquals(0,alg.splits.size);
	}