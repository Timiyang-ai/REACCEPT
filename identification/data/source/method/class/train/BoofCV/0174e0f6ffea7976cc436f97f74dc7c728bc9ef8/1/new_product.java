public static void computeNormalization(List<Point2D_F64> points, RowMatrix_F64 N )
	{
		double meanX1 = 0;
		double meanY1 = 0;

		for( Point2D_F64 p : points ) {
			meanX1 += p.x;
			meanY1 += p.y;
		}

		meanX1 /= points.size();
		meanY1 /= points.size();

		double stdX1 = 0;
		double stdY1 = 0;

		for( Point2D_F64 p : points ) {
			double dx = p.x - meanX1;
			double dy = p.y - meanY1;
			stdX1 += dx*dx;
			stdY1 += dy*dy;
		}

		stdX1 = Math.sqrt(stdX1/points.size());
		stdY1 = Math.sqrt(stdY1/points.size());
		N.zero();

		N.set(0, 0, 1.0 / stdX1);
		N.set(1, 1, 1.0 / stdY1);
		N.set(0, 2, -meanX1 / stdX1);
		N.set(1, 2, -meanY1 / stdY1);
		N.set(2, 2, 1.0);
	}