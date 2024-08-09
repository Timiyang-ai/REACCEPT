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
		int corner2 = findCorner(corner0,corner1,contour,corners);
		int corner3 = findCorner(corner1,corner0,contour,corners);

		// refine the corner estimates by maximizing the acute angle
		corner0 = refineCorner(corner0, refinementRadius,contour);
		corner1 = refineCorner(corner1, refinementRadius,contour);
		corner2 = refineCorner(corner2, refinementRadius,contour);
		corner3 = refineCorner(corner3, refinementRadius,contour);

		corners.clear();
		corners.add(contour.get(corner0));
		corners.add(contour.get(corner1));
		corners.add(contour.get(corner2));
		corners.add(contour.get(corner3));

		// sort the corners to make future calculations easier
		sortByAngleCCW(center, corners);

		return corners;
	}