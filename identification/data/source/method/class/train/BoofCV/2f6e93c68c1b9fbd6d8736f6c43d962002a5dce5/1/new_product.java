public boolean process( T input , GrayU8 binary ) {
		boundPolygon.vertexes.reset();

		detectorSquare.process(input, binary);
		List<DetectPolygonFromContour.Info> found = detectorSquare.getPolygonInfo();

		for (int i = 0; i < found.size(); i++) {
			DetectPolygonFromContour.Info f = found.get(i);
//			System.out.println("  touches "+f.contourTouchesBorder+"  size "+f.polygon.size());

			// get a better fit onto the contour
			detectorSquare.refineContour(f);

			// expand the polygons to compensate for the binary image being dilated
			adjustBeforeOptimize(f.polygon,f.borderCorners);

			// now refine the polygon fit
//			System.out.println("before "+f.polygon);
			if( !detectorSquare.refineUsingEdge(f) ) {
				System.out.println("Refine failed");
			}
//			System.out.println("       "+f.polygon.areaSimple());
		}

		clusters = s2c.process(found);

		c2g.process(clusters);
		List<SquareGrid> grids = c2g.getGrids().toList();

		for (int i = 0; i < grids.size(); i++) {
			SquareGrid grid = grids.get(i);
			if( grid.rows == numCols && grid.columns == numRows ) {
				tools.transpose(grid);
			}
			if( grid.rows == numRows && grid.columns == numCols ) {
				// this detector requires that the (0,0) grid cell has a square inside of it
				if( grid.get(0,0) == null ){
					if( grid.get(0,-1) != null ) {
						tools.flipColumns(grid);
					} else if( grid.get(-1,0) != null ) {
						tools.flipRows(grid);
					} else {
						continue;
					}
				}

				// make sure its in the expected orientation
				if( !ensureCCW(grid) )
					continue;

				// If symmetric, ensure that the (0,0) is closest to top-left image corner
				putIntoCanonical(grid);

				// now extract the calibration points
				return computeCalibrationPoints(grid);
			}
		}

		return false;
	}