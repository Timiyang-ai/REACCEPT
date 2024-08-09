@Test
	void score_SinglePath() {
		Planar<GrayU16> costYXD = new Planar<>(GrayU16.class,rangeD,width,height);
		GImageMiscOps.fillUniform(costYXD,rand,0,SgmDisparityCost.MAX_COST);

		SgmCostAggregation alg = new SgmCostAggregation();

		alg.init(costYXD);
		int before = countNotZero(alg.aggregated);

		alg.score(0,0,1,1);

		// the length is the number of elements to expect
		int length = alg.computePathLength(0,0,1,1);
		int foundCount = countNotZero(alg.aggregated);

		// for each point in the path it computed the aggregated cost
		assertEquals(length*rangeD,foundCount);

		// TODO check the actual value using a brute force approach
	}