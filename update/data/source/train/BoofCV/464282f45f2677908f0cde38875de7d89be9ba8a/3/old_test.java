@Test
	public void splitPixels() {
		SplitMergeLineFitLoop alg = new SplitMergeLineFitLoop(2.99,100,100);
		alg.contour = new ArrayList<Point2D_I32>();
		for( int i = 0; i < 10; i++ )
			alg.contour.add( new Point2D_I32(i,0));
		alg.N = alg.contour.size();
		alg.contour.get(4).y = 3;// set it just above the threshold

		// tests which require no looping
		alg.splitPixels(0,3);
		assertEquals(0,alg.splits.size);
		alg.splitPixels(0,4);  // points before will be within threshold
		assertEquals(0,alg.splits.size);
		alg.splitPixels(0,5);
		assertEquals(1,alg.splits.size);
		assertEquals(4,alg.splits.data[0]);
		alg.splits.reset();
		alg.splitPixels(0,9);
		assertEquals(1,alg.splits.size);
		assertEquals(4,alg.splits.data[0]);

		// tests which require looping
		// Note: That multiple hits will be encountered since the line is more distance from points in this direction
		alg.splits.reset();
		alg.splitPixels(5, 7);
		assertEquals(0, alg.splits.size);
		alg.splitPixels(6, 9);// the line has a steep slope.  I guess two splits is reasonable
		assertEquals(2,alg.splits.size);
		assertEquals(4, alg.splits.data[1]);
		alg.splits.reset();
		alg.splitPixels(9, 4);
		assertEquals(0, alg.splits.size);
		alg.splitPixels(9, 5);
		assertEquals(1,alg.splits.size);

		// see if it handles these cases
		alg.splits.reset();
		alg.splitPixels(0,1);
		assertEquals(0,alg.splits.size);
		alg.splitPixels(9,1);
		assertEquals(0,alg.splits.size);
	}