int selectCorner( GridInfo info ) {

		info.lookupGridCorners(cornerList);

		int bestCorner = -1;
		double bestScore = Double.MAX_VALUE;
		boolean bestIsCornerSquare = false;

		for (int i = 0; i < cornerList.size(); i++) {
			Node n = cornerList.get(i);

			boolean corner = isCornerValidOrigin(n);

			// If there are no corner points which are valid corners, then any corner can be the origin if
			// allowNoCorner is true
			if( corner || ( !requireCornerSquares && !bestIsCornerSquare) ) {
				// sanity check the shape
				if( checkShape != null ) {
					if( i%2==0 ) {
						if( !checkShape.isValidShape(info.rows,info.cols)) {
							continue;
						}
					} else {
						if( !checkShape.isValidShape(info.cols,info.rows)) {
							continue;
						}
					}
				}

				// If the distance is to (0,0) pixel is smaller or this is a corner square and the other best
				// is not a corner square
				double distance = n.normSq();
				if( distance < bestScore || (!bestIsCornerSquare && corner )) {
					bestIsCornerSquare |= corner;
					bestScore = distance;
					bestCorner = i;
				}
			}
		}
		info.hasCornerSquare = bestIsCornerSquare;
		return bestCorner;
	}