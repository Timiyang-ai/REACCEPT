@Test
	void computeCostInnerD() {
		Planar<GrayU16> costYXD = new Planar<>(GrayU16.class,rangeD,width,height);
		GImageMiscOps.fillUniform(costYXD,rand,0,SgmDisparityCost.MAX_COST);

		SgmCostAggregation alg = new SgmCostAggregation();
		alg.init(costYXD);
		short[] workCostLr = alg.workspace.get(0).workCostLr;
		for (int i = 0; i < workCostLr.length; i++) {
			workCostLr[i] = (short)rand.nextInt(SgmDisparityCost.MAX_COST);
		}

		GrayU16 costXD = costYXD.getBand(2);

		int x = rangeD + 2;  // x-value in image
		int pathI = 3;       // location along the path

		int idxCost = costXD.getIndex(0,x); // x=row, d=col
		int idxWork = alg.lengthD*pathI;

		// Compute the cost using this algorithm
		alg.computeCostInnerD(costXD,idxCost,idxWork, rangeD, workCostLr);

		// Now compare it to a brute force solution
		for (int d = 1; d < rangeD-1; d++) {
			int cost_p_d = costXD.get(d,x);

			int l0 = workArray(alg,pathI,d);
			int l1 = workArray(alg,pathI,d-1) + alg.penalty1;
			int l2 = workArray(alg,pathI,d+1) + alg.penalty1;
			int l3 = alg.penalty2;

			int v = min(min(min(l0,l1),l2),l3);

			int expected = cost_p_d + v;
			int found = workArray(alg,pathI+1,d);

			assertEquals(expected,found);
		}
	}