private void computePointsAndWeights(float slopeX, float slopeY, float x0, float y0, float tanX, float tanY) {
		float centerX = (float)center.x;
		float centerY = (float)center.y;

		for (int i = 0; i < numSamples; i++) {
			// find point on line
			float frac = i/(float)(numSamples-1);
			float x = x0 + slopeX*frac;
			float y = y0 + slopeY*frac;

			// compute sample point one pixel to the left of the line where the color should be different
			float leftX = x + tanX;
			float leftY = y + tanY;
			float rightX = x;
			float rightY = y;

			for (int j = 0; j < numTangent; j++) {
				// sample the value
				float valueLeft = interpolate.get(leftX,leftY);
				float valueLine = interpolate.get(rightX,rightY);

				// add the point to the list and convert into local coordinates
				samplePts.get(i).set(x-centerX,y-centerY);
				weights[i] = Math.max(0,sign*(valueLeft-valueLine));

				leftX += tanX;
				leftY += tanY;
				rightX -= tanX;
				rightY -= tanY;
			}
		}
	}