void scorePath(int x0 , int y0 , int dx , int dy ) {
		// there is no previous disparity score so simply fill the cost for d=0

		{
			final GrayU16 costXD = costYXD.getBand(y0);
			final int idxCost = costXD.getIndex(0,x0);   // C(0,0)
			final int localLengthD = helper.localDisparityRangeLeft(x0);
			for (int d = 0; d < localLengthD; d++) {
				workCostLr[d] = costXD.data[idxCost + d]; // Lr(0,d) = C(0,d)
				// no & 0xFFFF needed, short copy to short
			}
		}

		// Compute the cost of rest of the path recursively
		int lengthPath = computePathLength(x0, y0, dx, dy);
		int x = x0 + dx;
		int y = y0 + dy;

		for (int i = 1; i < lengthPath; i++, x += dx, y += dy) {
			// Read results from the previous location along the path
			int idxLrPrev = (i-1)*lengthD;

			// Don't consider disparity values that go outside the right image
			final int prevLengthD = helper.localDisparityRangeLeft(x-dx);

			// find the minimum path cost for all D in the previous point in path
			int minLrPrev = Integer.MAX_VALUE;
			for (int d = 0; d < prevLengthD; d++) {
				int cost = workCostLr[idxLrPrev+d] & 0xFFFF; // Lr(i,d)
				if( cost < minLrPrev )
					minLrPrev = cost;
			}

			// Index of cost for C(y,p0+i,0)
			final GrayU16 costXD = costYXD.getBand(y);
			final int idxCost = costXD.getIndex(0,x);
			final int localLengthD = helper.localDisparityRangeLeft(x);

			// Score the inner portion of disparity first to avoid bounds checks
			computeCostInnerD(costXD, idxCost, idxLrPrev, minLrPrev, localLengthD);

			// Now handle the borders at d=0 and d=N-1
			computeCostBorderD(idxCost,idxLrPrev,0,costXD,minLrPrev, localLengthD);
			computeCostBorderD(idxCost,idxLrPrev,localLengthD-1,costXD,minLrPrev, localLengthD);
		}

		saveWorkToAggregated(x0,y0,dx,dy,lengthPath);
	}