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

			if( (g.columns == innerCols && g.rows == innerRows) ||
					(g.columns == innerRows && g.rows == innerCols)) {
				if( inner != null )
					return false;
				inner = g;
			} else if( (g.columns == outerCols && g.rows == outerRows) ||
					(g.columns == outerRows && g.rows == outerCols)) {
				if( outer != null )
					return false;
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

		// find a corner to align the two grids by
		tools.boundingPolygon(inner, innerPolygon);
		outerOrigins.clear();
		listPossibleZeroNodes(outer, outerOrigins);
		selectSeedZero(outerOrigins,inner, innerPolygon);
		// now align the grids
		forceToZero(seedInner, inner);
		forceToZero(seedOuter, outer);

		// create one big grid for easier processing
		createUber(outer, inner, uberGrid);

		// put it into canonical order
		putIntoCanonical(uberGrid,inner,outer);
		tools.orderSquareCorners(inner);
		tools.orderSquareCorners(outer);

		// now extract the calibration points
		return getCalibrationPoints(uberGrid);
	}