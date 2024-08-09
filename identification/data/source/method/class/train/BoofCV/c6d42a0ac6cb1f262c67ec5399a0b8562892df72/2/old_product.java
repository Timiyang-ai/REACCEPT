private void optimize( Point2D_F64 a , Point2D_F64 b , LineGeneral2D_F64 foundLocal ) {

		float slopeX = (float)(b.x - a.x);
		float slopeY = (float)(b.y - a.y);

		// define the line segment which points will be sampled along.
		// don't sample too close to the corner since the line because less clear there and it can screw up results
		float x0 = (float)a.x + slopeX*lineBorder;
		float y0 = (float)a.y + slopeY*lineBorder;

		// truncate the slope
		slopeX *= (1.0f-2.0f*lineBorder);
		slopeY *= (1.0f-2.0f*lineBorder);

		// normalized tangent of sample distance length
		float tanX = -slopeY;
		float tanY = slopeX;

		float r = (float)Math.sqrt(tanX*tanX + tanY*tanY);
		tanX = tanX/r;
		tanY = tanY/r;

		// set up inputs into line fitting
		computePointsAndWeights(slopeX, slopeY, x0, y0, tanX, tanY);

		// fit line and convert into generalized format
		FitLine_F64.polar(samplePts, weights, polar);
		UtilLine2D_F64.convert(polar,foundLocal);
	}