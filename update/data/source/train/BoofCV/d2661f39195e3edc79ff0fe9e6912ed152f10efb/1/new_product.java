void scorePath(int x0 , int y0 , int dx , int dy ) {
		// there is no previous disparity score so simply fill the cost for d=0
		GrayU16 costXD = costYXD.getBand(y0);
		int idxCost = costXD.getIndex(0,x0);   // C(0,0)

		for (int d = 0; d < lengthD; d++) {
			workCostLr[d] = costXD.data[idxCost+d]; // Lr(0,d) = C(0,d)
		}

		// Compute the cost of rest of the path recursively
		int lengthPath = computePathLength(x0, y0, dx, dy);
		int x = x0 + dx;
		int y = y0 + dy;

		final int lengthD = this.lengthD;

		for (int i = 1; i < lengthPath; i++, x += dx, y += dy) {
			// Read results from the previous location along the path
			int idxLr = (i-1)*lengthD;

			// find the minimum path cost for all D in the previous point in path
			int minLrPrev = Integer.MAX_VALUE;
			for (int d = 0; d < lengthD; d++) {
				int cost = workCostLr[idxLr+d] & 0xFFFF; // Lr(i,d)
				if( cost < minLrPrev )
					minLrPrev = cost;
			}

			// Index of cost for C(y,p0+i,0)
			costXD = costYXD.getBand(y);
			idxCost = costXD.getIndex(0,x);

			// Score the inner portion of disparity first to avoid bounds checks
			computeCostInnerD(costXD, idxCost, idxLr, minLrPrev);

			// Now handle the borders
			computeCostBorderD(idxCost,idxLr,0,costXD,minLrPrev);
			computeCostBorderD(idxCost,idxLr,lengthD-1,costXD,minLrPrev);
		}

		saveWorkToAggregated(x0,y0,dx,dy,lengthPath);
	}