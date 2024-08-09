protected void optimize( Point2D_F64 a , Point2D_F64 b , LineGeneral2D_F64 foundLocal ) {

		float slopeX = (float)(b.x - a.x);
		float slopeY = (float)(b.y - a.y);
		float r = (float)Math.sqrt(slopeX*slopeX + slopeY*slopeY);
		// vector of unit length pointing in direction of the slope
		float unitX = slopeX/r;
		float unitY = slopeY/r;

		// define the line segment which points will be sampled along.
		// don't sample too close to the corner since the line because less clear there and it can screw up results
		float x0 = (float)a.x + unitX*lineBorder;
		float y0 = (float)a.y + unitY*lineBorder;

		// truncate the slope
		slopeX -= 2.0f*unitX*lineBorder;
		slopeY -= 2.0f*unitY*lineBorder;

		// normalized tangent of sample distance length
		float tanX = -unitY;
		float tanY = unitX;

		// set up inputs into line fitting
		computePointsAndWeights(slopeX, slopeY, x0, y0, tanX, tanY);

		// fit line and convert into generalized format
		FitLine_F64.polar(samplePts, weights, polar);
		UtilLine2D_F64.convert(polar,foundLocal);
	}