Element<Corner> selectCornerToRemove(List<Point2D_I32> contour , ErrorValue sideError , boolean loops ) {
		if( list.size() <= 3 )
			return null;

		// Pick the side that if split would improve the overall score the most
		Element<Corner> target,end;

		// if it loops any corner can be split. If it doesn't look the end points can't be split
		if( loops ) {
			target = list.getHead();
			end = null;
		} else {
			target = list.getHead().next;
			end = list.getTail();
		}

		Element<Corner> best = null;
		double bestScore = -Double.MAX_VALUE;

		while( target != end ) {
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