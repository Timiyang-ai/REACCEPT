public boolean fit( List<Point2D_I32> contour , GrowQueue_I32 corners )
	{
		searchRadius = Math.min(6,Math.max(contour.size()/12,3));

		int startCorner,endCorner;
		if( looping ) {
			startCorner = 0;
			endCorner = corners.size;
		} else {
			// the end point positions are fixed
			startCorner = 1;
			endCorner = corners.size-1;
		}

		boolean change = true;
		for( int iteration = 0; iteration < maxIterations && change; iteration++ ) {
			change = false;
			for (int i = startCorner; i < endCorner; i++) {
				int c0 = UtilShapePolygon.minus(i, 1, corners.size());
				int c2 = UtilShapePolygon.plus(i,1,corners.size());

				int improved = optimize(contour, corners.get(c0), corners.get(i), corners.get(c2));
				if( improved != corners.get(i)) {
					corners.set(i,improved);
					change = true;
				}
			}
		}

		return true;
	}