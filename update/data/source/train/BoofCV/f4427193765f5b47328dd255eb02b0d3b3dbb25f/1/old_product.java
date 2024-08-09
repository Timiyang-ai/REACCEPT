public List<Point2D_I32> process( List<Point2D_I32> contour ) {
		// order points in clockwise order
		Point2D_I32 center = findAverage(contour);
		sortByAngleCCW(center, contour);

		// find the first corner
		int corner0 = findFarthest( contour.get(0) , contour );
		// and the second
		int corner1 = findFarthest( contour.get(corner0) , contour );

		// now the other corners are harder
		List<Point2D_I32> corners = new ArrayList<Point2D_I32>();

		corners.add( contour.get(corner0));
		corners.add( contour.get(corner1));

		// find points which maximize the inlier to a line model and are not close to existing points
		findCorner(corner0,corner1,contour,corners);
		findCorner(corner1,corner0,contour,corners);

		// sort the corners to make future calculations easier
		sortByAngleCCW(center, corners);

		return corners;
	}