public static void computeNormalization(List<AssociatedPair> points, DenseMatrix64F N1, DenseMatrix64F N2)
	{
		double meanX1 = 0; double meanY1 = 0;
		double meanX2 = 0; double meanY2 = 0;

		for( AssociatedPair p : points ) {
			meanX1 += p.keyLoc.x;  meanY1 += p.keyLoc.y;
			meanX2 += p.currLoc.x; meanY2 += p.currLoc.y;
		}

		meanX1 /= points.size(); meanY1 /= points.size();
		meanX2 /= points.size(); meanY2 /= points.size();

		double stdX1 = 0; double stdY1 = 0;
		double stdX2 = 0; double stdY2 = 0;

		for( AssociatedPair p : points ) {
			double dx = p.keyLoc.x - meanX1;
			double dy = p.keyLoc.y - meanY1;
			stdX1 += dx*dx;
			stdY1 += dy*dy;

			dx = p.currLoc.x - meanX2;
			dy = p.currLoc.y - meanY2;
			stdX2 += dx*dx;
			stdY2 += dy*dy;
		}

		stdX1 = Math.sqrt(stdX1/points.size()); stdY1 = Math.sqrt(stdY1/points.size());
		stdX2 = Math.sqrt(stdX2/points.size()); stdY2 = Math.sqrt(stdY2/points.size());

		N1.zero(); N2.zero();

		N1.set(0,0,1.0/stdX1);
		N1.set(1,1,1.0/stdY1);
		N1.set(0,2,-meanX1/stdX1);
		N1.set(1,2,-meanY1/stdY1);
		N1.set(2,2,1.0);

		N2.set(0,0,1.0/stdX2);
		N2.set(1,1,1.0/stdY2);
		N2.set(0,2,-meanX2/stdX2);
		N2.set(1,2,-meanY2/stdY2);
		N2.set(2,2,1.0);
	}