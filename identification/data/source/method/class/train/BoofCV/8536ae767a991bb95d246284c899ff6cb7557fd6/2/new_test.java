@Test
	void process_MultipleCalls() {
		Planar<GrayU16> costYXD = new Planar<>(GrayU16.class,width,height,12);
		GImageMiscOps.fillUniform(costYXD,rand,0,100);

		SgmCostAggregation alg = new SgmCostAggregation();

		for( int paths : new int[]{1,2,4,8,16}) {
			alg.setPathsConsidered(paths);
			alg.process(costYXD);
			Planar<GrayU16> expected = alg.getAggregated().clone();
			alg.process(costYXD);
			Planar<GrayU16> found = alg.getAggregated();

			BoofTesting.assertEquals(expected,found,0.0);
		}
	}