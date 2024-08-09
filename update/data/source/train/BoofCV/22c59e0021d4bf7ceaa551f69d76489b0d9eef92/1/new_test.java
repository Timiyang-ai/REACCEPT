@Test
	public void computeHistogramBorder_outside() {
		int numSamples = 10;

		InterleavedF32 image = new InterleavedF32(40,50,3);
		DummyInterpolate interp = new DummyInterpolate();
		RectangleRotate_F32 rect = new RectangleRotate_F32(4,5,10,20,0);
		LocalWeightedHistogramRotRect alg = new LocalWeightedHistogramRotRect(numSamples,3,12,3,255,interp);

	    alg.c = 1; alg.s = 0;

		alg.computeHistogramBorder(image,rect);

		int numInside = 0;
		int i = 0;
		for( int y = 0; y < numSamples; y++ ) {
			for( int x = 0; x < numSamples; x++ , i++ ) {
				alg.squareToImageSample((float)x/(numSamples-1)-0.5f,(float)y/(numSamples-1)-0.5f,rect);

				boolean inside = alg.imageX >= 0 && alg.imageX < 40 && alg.imageY >= 0 && alg.imageY < 50;

				if( inside ) {
					numInside++;
					assertTrue(alg.sampleHistIndex[i] >= 0 );
					assertTrue(alg.histogram[alg.sampleHistIndex[i]] > 0 );
				} else {
					assertTrue(alg.sampleHistIndex[i] == -1 );
				}
			}
		}

		assertTrue(numInside != numSamples*numSamples);

	}