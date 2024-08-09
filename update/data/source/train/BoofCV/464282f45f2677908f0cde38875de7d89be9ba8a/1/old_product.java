public List<List<Point2D_I32>> fitPolygon( boolean external , double toleranceDist, double toleranceAngle ) {
		List<List<Point2D_I32>> polygons = new ArrayList<List<Point2D_I32>>();

		int numIterations = 20;

		if( external ) {
			List<PointIndex_I32> output = ShapeFittingOps.
					fitPolygon(contour.external, true, toleranceDist, toleranceAngle, numIterations);

			List<Point2D_I32> poly = new ArrayList<Point2D_I32>();
			for( PointIndex_I32 p : output ) {
				poly.add( new Point2D_I32(p.x,p.y));
			}
			polygons.add(poly);
		} else {
			for( List<Point2D_I32> i : contour.internal ) {
				List<PointIndex_I32> output = ShapeFittingOps.
						fitPolygon(i, true, toleranceDist, toleranceAngle, numIterations);

				List<Point2D_I32> poly = new ArrayList<Point2D_I32>();
				for (PointIndex_I32 p : output) {
					poly.add(new Point2D_I32(p.x, p.y));
				}
				polygons.add(poly);
			}
		}

		return polygons;
	}