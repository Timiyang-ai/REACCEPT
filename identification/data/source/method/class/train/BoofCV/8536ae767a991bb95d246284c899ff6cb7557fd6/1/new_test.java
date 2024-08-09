@Test
	void scorePath() {
		int x0=0,y0=0,dx=1,dy=1;

		Planar<GrayU16> costYXD = new Planar<>(GrayU16.class,rangeD,width,height);
		GImageMiscOps.fillUniform(costYXD,rand,1,SgmDisparityCost.MAX_COST);

		SgmCostAggregation alg = new SgmCostAggregation();

		alg.init(costYXD);
		GImageMiscOps.fill(alg.aggregated,0xBEEF);
		short[] workCostlr = new short[width*rangeD];
		alg.scorePath(x0,y0,dx,dy, workCostlr);

		// the length is the number of elements to expect
		int foundCount = countNotValue(alg.aggregated,0xBEEF);

		// Find the number of non-zero elements
		int length = alg.computePathLength(x0,y0,dx,dy);
		int expected = 0;
		for (int i = 0, x=x0,y=y0; i < length; i++,x+=dx,y+=dy) {
			expected += alg.helper.localDisparityRangeLeft(x);
		}
		expected -= length; // at least one element will be zero for each element in the path

		// for each point in the path it computed the aggregated cost
		assertEquals(expected,foundCount);
	}