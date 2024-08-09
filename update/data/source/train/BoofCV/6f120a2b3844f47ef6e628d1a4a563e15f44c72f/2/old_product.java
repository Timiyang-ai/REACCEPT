public void computeDescriptor( int cx , int cy , TupleDesc_F64 desc ) {

		desc.fill(0);

		int widthPixels = widthSubregion*widthGrid;
		int radius = widthPixels/2;

		ImageFloat32 image = null;

		for (int i = 0; i < widthPixels; i++) {
			int pixelIndex = (cy-radius+i)*image.stride + (cx-radius);

			float subY = i/(float)widthSubregion;

			for (int j = 0; j < widthPixels; j++, pixelIndex++ ) {
				float subX = j/(float)widthSubregion;

				float spacialDX = imageDerivX.getF(pixelIndex);
				float spacialDY = imageDerivY.getF(pixelIndex);

				double angle = UtilAngle.domain2PI(Math.atan2(spacialDX,spacialDY));

				float weightGaussian = gaussianWeight[i*widthPixels+j];
				float weightGradient = (float)Math.sqrt(spacialDX*spacialDX + spacialDY*spacialDY);

				// trilinear interpolation intro descriptor
				trilinearInterpolation(weightGaussian*weightGradient,subX,subY,angle,desc);
			}
		}
	}