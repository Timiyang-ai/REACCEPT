@Test
	void scoreDirection_minimum() {
		// Construct the cost tensor with an obvious minimum
		int targetD = rangeD/2;
		Planar<GrayU16> costYXD = new Planar<>(GrayU16.class,rangeD,width,height);
		for (int y = 0; y < height; y++) {
			GrayU16 costXD = costYXD.getBand(y);
			for (int x = 0; x < width; x++) {
				for (int d = 0; d < rangeD; d++) {
					costXD.set(d,x, targetD==d?100:1000);
				}
			}
		}

		SgmCostAggregation alg = new SgmCostAggregation();
		alg.init(costYXD);
		alg.scoreDirection(1,0);
		alg.scoreDirection(-1,0);

		Planar<GrayU16> aggregatedYXD = alg.getAggregated();

		// see if the minimum in the aggregated cost is at the expected location
		for (int y = 0; y < height; y++) {
			GrayU16 aggregatedXD = aggregatedYXD.getBand(y);
			for (int x = 0; x < width; x++) {
				int bestD = -1;
				int bestCost = Integer.MAX_VALUE;
				for (int d = 0; d < rangeD; d++) {
					if( aggregatedXD.get(d,x) < bestCost ) {
						bestCost = aggregatedXD.get(d,x);
						bestD = d;
					}
				}
				assertEquals(targetD,bestD);
//				assertEquals(200,bestCost);
			}
		}
	}