protected void computePointsAndWeights(float slopeX, float slopeY, float x0, float y0, float tanX, float tanY) {
		float centerX = (float)center.x;
		float centerY = (float)center.y;

		for (int i = 0; i < lineSamples; i++ ) {
			// find point on line and shift it over to the first sample point
			float frac = i/(float)(lineSamples -1);
			float x = x0 + slopeX*frac + sampleRadius*tanX;
			float y = y0 + slopeY*frac + sampleRadius*tanY;

			int indexPts = i*(values.length-1);

			values[0] = interpolate.get(x,y);
			for (int j = 1; j < values.length; j++) {
				x -= tanX;
				y -= tanY;

				// sample the value
				values[j] = interpolate.get(x,y);

				// add the point to the list and convert into local coordinates
				samplePts.get(indexPts+j-1).set(x-centerX,y-centerY);
			}

			// compute the weights using the difference between adjacent sample points
			// the weight should be maximized if the right sample point is inside the square
			for (int j = 0; j < values.length-1; j++) {
				weights[indexPts+j] = Math.max(0,sign*(values[j]-values[j+1]));
			}
		}
	}