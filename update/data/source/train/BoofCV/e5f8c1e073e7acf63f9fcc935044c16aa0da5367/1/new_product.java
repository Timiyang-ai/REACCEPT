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
			// first make sure orientation constraint is maintained
			if( isClockWise(g)) {
				flipHorizontal(g);
			}

			int numRotationsCCW = closestCorner4(g);
			if( g.columns == g.rows ) {
				for (int i = 0; i < numRotationsCCW; i++) {
					rotateGridCCW(g);
				}
			} else if( numRotationsCCW == 2 ){
				// only two valid solutions.  rotate only if the other valid solution is better
				rotateGridCCW(g);
				rotateGridCCW(g);
			}
		} else if( g.columns%2 == 1 ) {
			// only two solutions.  Go with the one which maintains orientation constraint
			if( isClockWise(g)) {
				flipHorizontal(g);
			}
		} else if( g.rows%2 == 1 ) {
			// only two solutions.  Go with the one which maintains orientation constraint
			if( isClockWise(g)) {
				flipVertical(g);
			}
		}
	}