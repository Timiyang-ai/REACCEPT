private void computeRawDescriptor(double c_x, double c_y, double sigma, double orientation) {
		double c = Math.cos(orientation);
		double s = Math.sin(orientation);

		int sampleWidth = widthGrid*widthSubregion;
		// compute radius and ensure its symmetric for even and odd cases
		double sampleRadius = sampleWidth/2-(1-(sampleWidth%2))/2.0;

		double sampleToPixels = sigma*sigmaToPixels;

		for (int sampleY = 0; sampleY < sampleWidth; sampleY++) {
			float subY = sampleY/widthSubregion;
			double y = sampleToPixels*(sampleY-sampleRadius);

			for (int sampleX = 0; sampleX < sampleWidth; sampleX++) {
				// coordinate of samples in terms of sub-region.  Center of sample point, hence + 0.5f
				float subX = sampleX/widthSubregion;
				// recentered local pixel sample coordinate
				double x = sampleToPixels*(sampleX-sampleRadius);

				// pixel coordinate in the image that is to be sampled.  Note the rounding
				int pixelX = (int)(x*c - y*s + c_x + 0.5);
				int pixelY = (int)(x*s + y*c + c_y + 0.5);

				// skip pixels outside of the image
				if( imageDerivX.isInBounds(pixelX,pixelY) ) {
					// spacial image derivative at this point
					float spacialDX = imageDerivX.unsafe_get(pixelX, pixelY);
					float spacialDY = imageDerivY.unsafe_get(pixelX, pixelY);

					double angle = UtilAngle.domain2PI(Math.atan2(spacialDY,spacialDX));

					float weightGaussian = gaussianWeight[sampleY*sampleWidth+sampleX];
					float weightGradient = (float)Math.sqrt(spacialDX*spacialDX + spacialDY*spacialDY);

					// trilinear interpolation intro descriptor
					trilinearInterpolation(weightGaussian*weightGradient,subX,subY,angle);
				}
			}
		}
	}