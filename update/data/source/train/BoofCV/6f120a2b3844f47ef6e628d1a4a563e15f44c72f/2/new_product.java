public void computeDescriptor( int cx , int cy , TupleDesc_F64 desc  ) {

		desc.fill(0);

		int widthPixels = widthSubregion*widthGrid;
		int radius = widthPixels/2;

		for (int i = 0; i < widthPixels; i++) {
			int angleIndex = (cy-radius+i)*savedAngle.width + (cx-radius);

			float subY = i/(float)widthSubregion;

			for (int j = 0; j < widthPixels; j++, angleIndex++ ) {
				float subX = j/(float)widthSubregion;

				double angle = savedAngle.data[angleIndex];

				float weightGaussian = gaussianWeight[i*widthPixels+j];
				float weightGradient = savedMagnitude.data[angleIndex];

				// trilinear interpolation intro descriptor
				trilinearInterpolation(weightGaussian*weightGradient,subX,subY,angle,desc);
			}
		}

		massageDescriptor(desc);
	}