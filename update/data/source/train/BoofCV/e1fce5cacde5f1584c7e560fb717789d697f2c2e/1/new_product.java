public boolean process( T input , ImageUInt8 binary ) {
		boundPolygon.vertexes.reset();

		detectorSquare.process(input, binary);

		FastQueue<Polygon2D_F64> found = detectorSquare.getFound();

		List<List<SquareNode>> clusters = s2c.process(found.toList());

		c2g.process(clusters);
		List<SquareGrid> grids = c2g.getGrids();

		// find inner and outer grids of squares
		SquareGrid inner = null;
		SquareGrid outer = null;

		for (int i = 0; i < grids.size(); i++) {
			SquareGrid g = grids.get(i);

			if( inner == null && ((g.columns == innerCols && g.rows == innerRows) ||
					(g.columns == innerRows && g.rows == innerCols))) {
				inner = g;
			} else if( outer == null && ((g.columns == outerCols && g.rows == outerRows) ||
					(g.columns == outerRows && g.rows == outerCols))) {
				outer = g;
			}
		}

		if( inner == null || outer == null )
			return false;

		// make sure the rows/columns are correctly aligned
		if( inner.columns != innerCols ) {
			tools.transpose(inner);
		}

		if( outer.columns != outerCols ) {
			tools.transpose(outer);
		}

		// make sure the grids are in counter clockwise order
		if( tools.checkFlip(inner)) {
			tools.flipRows(inner);
		}
		if( tools.checkFlip(outer)) {
			tools.flipRows(outer);
		}

		// find a corner to align the two grids by
		tools.boundingPolygon(inner, innerPolygon);
		selectZeroSeed(inner, outer, innerPolygon);
		// now align the two grids with adjacent zeros
		forceToZero(seedInner, inner);
		forceToZero(seedOuter, outer);

		// create one big grid for easier processing
		createUber(inner, outer, uberGrid);

		// put it into canonical order
		putIntoCanonical(uberGrid);
		orderUberCorners(uberGrid);

		// now extract the calibration points
		return computeCalibrationPoints(uberGrid);
	}