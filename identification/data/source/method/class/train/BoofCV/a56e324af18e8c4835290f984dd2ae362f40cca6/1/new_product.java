public static void computeNormalization(List<Point2D_F64> points, NormalizationPoint2D normalize )
	{
		double meanX = 0;
		double meanY = 0;

		for( Point2D_F64 p : points ) {
			meanX += p.x;
			meanY += p.y;
		}

		meanX /= points.size();
		meanY /= points.size();

		double stdX = 0;
		double stdY = 0;

		for( Point2D_F64 p : points ) {
			double dx = p.x - meanX;
			double dy = p.y - meanY;
			stdX += dx*dx;
			stdY += dy*dy;
		}

		normalize.meanX = meanX;
		normalize.meanY = meanY;

		normalize.stdX = Math.sqrt(stdX/points.size());
		normalize.stdY = Math.sqrt(stdY/points.size());
	}