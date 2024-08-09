@Override
	protected void putGridIntoCanonical(Grid g ) {
		// first put it into a plausible solution
		if( g.columns != numCols ) {
			rotateGridCCW(g);
		}

		if( isClockWise(g)) {
			flipHorizontal(g);
		}
	}