void putGridIntoCanonical(Grid g ) {
		// first put it into a plausible solution
		if( g.columns != numCols ) {
			rotateGridCCW(g);
		}

		if( g.get(0,0) == null ) {
			reverse(g);
		}

		// select the best corner for canonical
		if( g.columns%2 == 1 && g.rows%2 == 1) {
			int numRotationsCCW = closestCorner4(g);

			for (int i = 0; i < numRotationsCCW; i++) {
				rotateGridCCW(g);
			}
		} else if( g.columns%2 == 1 ) {
			if( g.get(0,0).center.normSq() > g.get(0,g.columns-1).center.normSq() ) {
				flipHorizontal(g);
			}
		} else if( g.rows%2 == 1 ) {
			if( g.get(0,0).center.normSq() > g.get(g.rows-1,0).center.normSq() ) {
				flipVertical(g);
			}
		}
	}