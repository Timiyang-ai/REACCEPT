@Test
	void score_SinglePath() {
		Planar<GrayU16> costYXD = new Planar<>(GrayU16.class,width,height, rangeD);
		GImageMiscOps.fillUniform(costYXD,rand,0,SgmDisparityCost.MAX_COST);

		SgmCostAggregation alg = new SgmCostAggregation();

		alg.init(costYXD);
		alg.score(0,0,1,1);

		// the length is the number of elements to expect
		int length = alg.computePathLength(0,0,1,1);
		int foundCount = countNotZero(alg.aggregated);

		assertEquals(length,foundCount);
	}