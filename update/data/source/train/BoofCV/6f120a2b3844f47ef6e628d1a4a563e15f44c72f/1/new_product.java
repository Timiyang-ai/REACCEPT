void computeRawDescriptor(double c_x, double c_y, double sigma, double orientation) {
		double c = Math.cos(orientation);
		double s = Math.sin(orientation);

		float fwidthSubregion = widthSubregion;
		int sampleWidth = widthGrid*widthSubregion;
		double sampleRadius = sampleWidth/2;

		double sampleToPixels = sigma*sigmaToPixels;

		Deriv image = (Deriv)imageDerivX.getImage();

		for (int sampleY = 0; sampleY < sampleWidth; sampleY++) {
			float subY = sampleY/fwidthSubregion;
			double y = sampleToPixels*(sampleY-sampleRadius);

			for (int sampleX = 0; sampleX < sampleWidth; sampleX++) {
				// coordinate of samples in terms of sub-region.  Center of sample point, hence + 0.5f
				float subX = sampleX/fwidthSubregion;
				// recentered local pixel sample coordinate
				double x = sampleToPixels*(sampleX-sampleRadius);

				// pixel coordinate in the image that is to be sampled.  Note the rounding
				// If the pixel coordinate is -1 < x < 0 then it will round to 0 instead of -1, but the rounding
				// method below is WAY faster than Math.round() so this is a small loss.
				int pixelX = (int)(x*c - y*s + c_x + 0.5);
				int pixelY = (int)(x*s + y*c + c_y + 0.5);

				// skip pixels outside of the image
				if( image.isInBounds(pixelX,pixelY) ) {
					// spacial image derivative at this point
					float spacialDX = imageDerivX.unsafe_getF(pixelX, pixelY);
					float spacialDY = imageDerivY.unsafe_getF(pixelX, pixelY);

					double adjDX =  c*spacialDX + s*spacialDY;
					double adjDY = -s*spacialDX + c*spacialDY;

					double angle = UtilAngle.domain2PI(Math.atan2(adjDY,adjDX));

					float weightGaussian = gaussianWeight[sampleY*sampleWidth+sampleX];
					float weightGradient = (float)Math.sqrt(spacialDX*spacialDX + spacialDY*spacialDY);

					// trilinear interpolation intro descriptor
					trilinearInterpolation(weightGaussian*weightGradient,subX,subY,angle, descriptor);
				}
			}
		}
	}