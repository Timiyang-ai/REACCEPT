void computeCostInnerD(GrayU16 costXD, int idxCost, int idxLr, int minLrPrev) {
		final int lengthD = this.lengthD;
		idxLr += 1; // start at d=1
		for (int d = 1; d < lengthD-1; d++, idxLr++) {
			int cost = costXD.data[idxCost+d] & 0xFFFF; // C(p,d)

			int b = workCostLr[idxLr-1]&0xFFFF; // Lr(p-r,d-1)
			int a = workCostLr[idxLr  ]&0xFFFF; // Lr(p-r,d)
			int c = workCostLr[idxLr+1]&0xFFFF; // Lr(p-r,d+1)

			// Add penalty terms
			b += penalty1;
			c += penalty1;

			// Find the minimum of the three scores
			if( b < a )
				a = b;
			if( c < a )
				a = c;
			if( minLrPrev + penalty2 < c )
				a = minLrPrev + penalty2;

			// minCostPrev is done to reduce the rate at which the cost increases
			workCostLr[idxLr+lengthD] = (short)(cost + a - minLrPrev);
			// Lr(p,d) = above
		}
	}