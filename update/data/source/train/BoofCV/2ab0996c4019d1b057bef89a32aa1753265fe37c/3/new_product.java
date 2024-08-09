void scorePath(int x0 , int y0 , int dx , int dy , short[] workCostLr) {
		// there is no previous disparity score so simply fill the cost for d=0

		{
			int minCost = Integer.MAX_VALUE;
			final GrayU16 costXD = costYXD.getBand(y0);
			final int idxCost = costXD.getIndex(0,x0);   // C(0,0)
			final int localLengthD = helper.localDisparityRangeLeft(x0);
			for (int d = 0; d < localLengthD; d++) {
				int v = costXD.data[idxCost + d]&0xFFFF; // Lr(0,d) = C(0,d)
				workCostLr[d] = (short)v;
				minCost = Math.min(minCost,v);
			}
			// The modified equation 13. Cost Equation 12 - min[k] Lr(p,k)
			for (int d = 0; d < localLengthD; d++) {
				workCostLr[d] = (short)((workCostLr[d]&0xFFFF)-minCost);
			}
		}

		// Compute the cost of rest of the path recursively
		int lengthPath = computePathLength(x0, y0, dx, dy);
		int x = x0 + dx;
		int y = y0 + dy;

		for (int i = 1; i < lengthPath; i++, x += dx, y += dy) {

			// Index of cost for C(y,p0+i,0)
			final GrayU16 costXD = costYXD.getBand(y);
			final int idxCost = costXD.getIndex(0,x);
			final int lengthLocalD = helper.localDisparityRangeLeft(x);

			// Index for the previous cost in this path
			int idxLrPrev = (i-1)*lengthD;

			// Score the inner portion of disparity first to avoid bounds checks
			computeCostInnerD(costXD, idxCost, idxLrPrev, lengthLocalD, workCostLr);

			// Now handle the borders at d=0 and d=N-1
			computeCostBorderD(idxCost,idxLrPrev,0,costXD, lengthLocalD, workCostLr);
			computeCostBorderD(idxCost,idxLrPrev,lengthLocalD-1,costXD, lengthLocalD, workCostLr);

			// The modified equation 13. Cost Equation 12 - min[k] Lr(p,k)
			int minCost = Integer.MAX_VALUE;
			int idxLr = i*lengthD;
			for (int d = 0; d < lengthLocalD; d++) {
				minCost = Math.min(minCost,workCostLr[idxLr+d]&0xFFFF);
			}
			for (int d = 0; d < lengthLocalD; d++) {
				workCostLr[idxLr+d] = (short)((workCostLr[idxLr+d]&0xFFFF)- minCost);
			}

		}

		saveWorkToAggregated(x0,y0,dx,dy,lengthPath, workCostLr);
	}