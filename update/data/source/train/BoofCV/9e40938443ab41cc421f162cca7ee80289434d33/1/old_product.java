void score( int x0 , int y0 , int dx , int dy ) {
		// there is no previous score so simply fill
		GrayU16 costXD = costYXD.getBand(y0);
		int idxCost = costXD.getIndex(x0,0);

		for (int d = 0; d < lengthD; d++) {
			pathWork[d] = costXD.data[idxCost+d];
		}

		// Compute the cost of rest of the path recursively
		int lengthPath = computePathLength(x0, y0, dx, dy);
		int x = x0 + dx;
		int y = y0 + dy;

		final int lengthD = this.lengthD;

		for (int i = 1; i < lengthPath; i++) {
			costXD = costYXD.getBand(y);
			idxCost = costXD.getIndex(x,0);

			// Read results from the previous location along the path
			int idxWork = (i-1)*lengthD;

			// find the minimum cost for all D in the previous
			int minCostPrev = Integer.MAX_VALUE;
			for (int d = 0; d < lengthD; d++) {
				int cost = costXD.data[idxCost+d] & 0xFFFF;
				if( cost < minCostPrev )
					minCostPrev = cost;
			}

			// Add penalty
			int minCostPrevTotal = minCostPrev + penalty2;

			// Score the inner portion of disparity first to avoid bounds checks
			computeCostInnerD(costXD, idxCost, idxWork, minCostPrev, minCostPrevTotal);

			// Now handle the borders
			computeCostBorderD(idxCost,idxWork,0,costXD,minCostPrev);
			computeCostBorderD(idxCost,idxWork,lengthD-1,costXD,minCostPrev);
		}

		saveWorkToAggregated(x0,y0,dx,dy,lengthPath);
	}