public void setTransform( PixelTransform2_F32 undistToDist ) {
		if( undistToDist != null ) {
			InterpolatePixelS<T> interpolate = FactoryInterpolation.bilinearPixelS(imageType, BorderType.EXTENDED);
			integralImage = new GImageGrayDistorted<>(undistToDist, interpolate);
		} else {
			integralImage = FactoryGImageGray.create(imageType);
		}
	}