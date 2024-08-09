@Override
	protected void putGridIntoCanonical(Grid g ) {
		// first put it into a plausible solution
		if( g.columns != numCols ) {
			rotateGridCCW(g);
		}

		if( isClockWise(g)) {
			flipHorizontal(g);
		}

		// pick the solutin which puts (0,0) coordinate the closest to the top left corner to resolve ambiguity
		if( g.columns == g.rows ) {
			closestCorner[0] = g.get(0,0).center.normSq();
			closestCorner[1] = g.get(numRows-1,0).center.normSq();
			closestCorner[2] = g.get(numRows-1,numCols-1).center.normSq();
			closestCorner[3] = g.get(0,numCols-1).center.normSq();

			int best = 0;
			for (int i = 0; i < 4; i++) {
				if( closestCorner[i] < closestCorner[best]) {
					best = i;
				}
			}

			for (int i = 0; i < best; i++) {
				rotateGridCCW(g);
			}
		} else {
			double d00 = g.get(0,0).center.normSq();
			double d11 = g.get(numRows-1,numCols-1).center.normSq();

			if( d11 < d00 ) {
				rotateGridCCW(g);
				rotateGridCCW(g);
			}
		}
	}