protected void splitPixels(int indexStart, int length) {
		// too short to split
		if( length < minimumSideLengthPixel)
			return;

		// end points of the line
		int indexEnd = (indexStart+length)%N;

		int splitOffset = selectSplitOffset(indexStart,length);

		if( splitOffset >= 0 ) {
//			System.out.println("  splitting ");
			splitPixels(indexStart, splitOffset);
			int indexSplit = (indexStart+splitOffset)%N;
			splits.add(indexSplit);
			splitPixels(indexSplit, circularDistance(indexSplit, indexEnd));
		}
	}