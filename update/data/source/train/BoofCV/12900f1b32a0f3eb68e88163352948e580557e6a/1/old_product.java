Element<Corner> selectCornerToRemove(List<Point2D_I32> contour , ErrorValue sideError ) {
		if( list.size() <= 3 )
			return null;

		Element<Corner> target = list.getHead();
		Element<Corner> best = null;
		double bestScore = -Double.MAX_VALUE;
		double bestSideError = -1;

		while( target != null ) {
			Element<Corner> p = previous(target);
			Element<Corner> n = next(target);

			// just contributions of the corners in question
			double before = (p.object.sideError + target.object.sideError)/2.0 + cornerScorePenalty;
			double after = computeSideError(contour, p.object.index, n.object.index);

			if( before-after > bestScore ) {
				bestScore = before-after;
				best = target;
				sideError.value = after;
			}
			target = target.next;
		}

		return best;
	}