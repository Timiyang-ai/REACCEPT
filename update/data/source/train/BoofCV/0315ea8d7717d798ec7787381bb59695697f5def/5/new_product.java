public static FitData<EllipseRotated_F64> fitEllipse_F64( List<Point2D_F64> points, int iterations ,
															  boolean computeError ,
															  FitData<EllipseRotated_F64> outputStorage ) {

		if( outputStorage == null ) {
			outputStorage = new FitData<>(new EllipseRotated_F64());
		}

		// Compute the optimal algebraic error
		FitEllipseAlgebraic algebraic = new FitEllipseAlgebraic();

		if( !algebraic.process(points)) {
			// could be a line or some other weird case. Create a crude estimate instead
			FitData<Circle2D_F64> circleData = averageCircle_F64(points,null,null);
			Circle2D_F64 circle = circleData.shape;
			outputStorage.shape.set(circle.center.x,circle.center.y,circle.radius,circle.radius,0);
		} else {
			UtilEllipse_F64.convert(algebraic.getEllipse(),outputStorage.shape);
		}

		// Improve the solution from algebraic into Euclidean
		if( iterations > 0 ) {
			RefineEllipseEuclideanLeastSquares leastSquares = new RefineEllipseEuclideanLeastSquares();
			leastSquares.setMaxIterations(iterations);
			leastSquares.refine(outputStorage.shape,points);
			outputStorage.shape.set( leastSquares.getFound() );
		}

		// compute the average Euclidean error if the user requests it
		if( computeError ) {
			ClosestPointEllipseAngle_F64 closestPoint = new ClosestPointEllipseAngle_F64(1e-8,100);
			closestPoint.setEllipse(outputStorage.shape);

			double total = 0;
			for( Point2D_F64 p : points ) {
				closestPoint.process(p);
				total += p.distance(closestPoint.getClosest());
			}
			outputStorage.error = total/points.size();
		} else {
			outputStorage.error = 0;
		}

		return outputStorage;
	}