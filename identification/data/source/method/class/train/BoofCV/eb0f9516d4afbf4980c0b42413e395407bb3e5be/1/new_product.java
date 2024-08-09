void computeHistogram(int c_x, int c_y, double sigma) {
		int r = (int)Math.ceil(sigma * sigmaToRadius);

		// specify the area being sampled
		bound.x0 = c_x - r;
		bound.y0 = c_y - r;
		bound.x1 = c_x + r + 1;
		bound.y1 = c_y + r + 1;

		// make sure it is contained in the image bounds
		BoofMiscOps.boundRectangleInside(derivX,bound);

		// clear the histogram
		Arrays.fill(histogramMag,0);
		Arrays.fill(histogramX,0);
		Arrays.fill(histogramY,0);

		// construct the histogram
		for( int y = bound.y0; y < bound.y1; y++ ) {
			// iterate through the raw array for speed
			int indexDX = derivX.startIndex + y*derivX.stride + bound.x0;
			int indexDY = derivY.startIndex + y*derivY.stride + bound.x0;

			for( int x = bound.x0; x < bound.x1; x++ ) {
				float dx = derivX.data[indexDX++];
				float dy = derivY.data[indexDY++];

				// edge intensity and angle
				double magnitude = Math.sqrt(dx*dx + dy*dy);
				double theta = UtilAngle.domain2PI(Math.atan2(dy,dx));

				// weight from gaussian
				double weight = computeWeight( x-c_x, y-c_y , sigma );

				// histogram index
				int h = (int)(theta / histAngleBin) % histogramMag.length;

				// update the histogram
				histogramMag[h] += magnitude*weight;
				histogramX[h] += dx*weight;
				histogramY[h] += dy*weight;
			}
		}
	}