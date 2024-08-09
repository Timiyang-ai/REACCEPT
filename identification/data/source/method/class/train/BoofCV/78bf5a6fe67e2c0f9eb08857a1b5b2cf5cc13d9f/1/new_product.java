void computeCostInnerD(GrayU16 costXD, int idxCost, int idxLrPrev, int minLrPrev, int lengthLocalD ) {
		idxLrPrev += 1; // start at d=1
		for (int d = 1; d < lengthLocalD-1; d++, idxLrPrev++) {
			int cost = costXD.data[idxCost+d] & 0xFFFF; // C(p,d)

			int b = workCostLr[idxLrPrev-1]&0xFFFF; // Lr(p-r,d-1)
			int a = workCostLr[idxLrPrev  ]&0xFFFF; // Lr(p-r,d  )
			int c = workCostLr[idxLrPrev+1]&0xFFFF; // Lr(p-r,d+1)

			// Add penalty terms
			b += penalty1;
			c += penalty1;

			// Find the minimum of the three scores
			if( b < a )
				a = b;
			if( c < a )
				a = c;
			if( minLrPrev + penalty2 < a )
				a = minLrPrev + penalty2;

			// minCostPrev is done to reduce the rate at which the cost increases
			workCostLr[idxLrPrev+this.lengthD] = (short)(cost + a - minLrPrev);
			// Lr(p,d) = above
		}
	}