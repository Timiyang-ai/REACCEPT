boolean findInitialTriangle(List<Point2D_I32> contour) {
		// find the first estimate for a corner
		int cornerSeed = findCornerSeed(contour);

		// see if it can reject the contour immediately
		if( convex ) {
			if( !isConvexUsingMaxDistantPoints(contour,0,cornerSeed))
				return false;
		}

		// Select the second corner.
		splitter.selectSplitPoint(contour,0,cornerSeed,resultsA);
		splitter.selectSplitPoint(contour,cornerSeed,0,resultsB);

		if( splitter.compareScore(resultsA.score,resultsB.score) >= 0 ) {
			addCorner(resultsA.index);
			addCorner(cornerSeed);
		} else {
			addCorner(cornerSeed);
			addCorner(resultsB.index);
		}

		// Select the third corner. Initial triangle will be complete now
		// the third corner will be the one which maximizes the distance from the first two
		int index0 = list.getHead().object.index;
		int index1 = list.getHead().next.object.index;
		int index2 = maximumDistance(contour,index0,index1);
		addCorner(index2);

		// enforce CCW requirement
		ensureTriangleOrder(contour);

		// Score each side
		Element<Corner> e = list.getHead();
		while( e != null ) {
			if (convex && !isSideConvex(contour, e))
				return false;

			Element<Corner> n = e.next;

			double error;
			if( n == null ) {
				error = computeSideError(contour,e.object.index, list.getHead().object.index);
			} else {
				error = computeSideError(contour,e.object.index, n.object.index);
			}
			e.object.sideError = error;
			e = n;
		}

		// Compute what would happen if a side was split
		e = list.getHead();
		while( e != null ) {
			computePotentialSplitScore(contour,e,list.size() < minSides);
			e = e.next;
		}

		return true;
	}