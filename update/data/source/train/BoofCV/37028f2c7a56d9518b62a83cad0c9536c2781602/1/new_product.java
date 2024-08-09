void computeCostInnerD(GrayU16 costXD, int idxCost, int idxLrPrev, int lengthLocalD ) {
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
			if( penalty2 < a )
				a = penalty2;

			// minCostPrev is done to reduce the rate at which the cost increases
			if( cost + a > Short.MAX_VALUE )
				throw new RuntimeException("Egads");
			workCostLr[idxLrPrev+this.lengthD] = (short)(cost + a);
			// Lr(p,d) = above
		}
	}