protected void splitPixels( int indexStart , int indexStop ) {
		// too short to split
		if( indexStart+1 >= indexStop )
			return;

		int indexSplit = selectSplitBetween(indexStart, indexStop);

		if( indexSplit >= 0 ) {
			splitPixels(indexStart, indexSplit);
			splits.add(indexSplit);
			splitPixels(indexSplit, indexStop);
		}
	}