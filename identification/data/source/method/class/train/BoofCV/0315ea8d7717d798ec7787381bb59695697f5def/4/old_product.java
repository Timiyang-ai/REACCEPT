public void various() {
		Helper detector = new Helper();
		detector.maximum = true;
		GeneralToInterestMulti<GrayF32,GrayF32> alg =
				new GeneralToInterestMulti<GrayF32,GrayF32>(detector,2.5, GrayF32.class,GrayF32.class);

		alg.detect(input);

		assertEquals(1,alg.getNumberOfSets());
		FoundPointSO set = alg.getFeatureSet(0);
		assertEquals(6,set.getNumberOfFeatures());
		for( int i = 0; i < set.getNumberOfFeatures(); i++ ) {
			assertEquals(2.5, set.getRadius(i),1e-8);
			assertEquals(0, set.getOrientation(i),1e-8);
		}

		assertEquals(1, detector.calledProcess);
		assertEquals(6, detector.getMaximums().size);
	}