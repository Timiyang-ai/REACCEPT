private boolean increaseNumberOfSidesByOne(List<Point2D_I32> contour) {
		LinkedList.Element<Corner> selected = selectCornerToSplit();

		// No side can be split
		if( selected == null )
			return false;

		// split the selected side and add a new corner
		Corner c = corners.grow();
		c.index = selected.object.splitLocation;
		LinkedList.Element<Corner> cornerE = list.insertAfter(selected,c);

		// compute the score for sides which just changed
		computePotentialSplitScore(contour,next(cornerE));
		computePotentialSplitScore(contour,previous(cornerE));

		// Save the results
		savePolyline();

		// See if new lines formed by split should be merged together with old adjacent lines
		considerAndRemoveCorner(contour,cornerE.next,getCurrentPolylineScore());
		considerAndRemoveCorner(contour,cornerE.previous,getCurrentPolylineScore());

		return true;
	}