protected void splitPixels( int indexStart , int indexStop ) {
		// too short to split
		if( indexStart+1 >= indexStop )
			return;

		// end points of the line
		Point2D_I32 a = contour.get(indexStart);
		Point2D_I32 c = contour.get(indexStop);

		line.p.set(a.x,a.y);
		line.slope.set(c.x-a.x,c.y-a.y);

		int indexSplit = selectSplitBetween(indexStart, indexStop);

		if( indexSplit >= 0 ) {
			splitPixels(indexStart, indexSplit);
			splits.add(indexSplit);
			splitPixels(indexSplit, indexStop);
		}
	}