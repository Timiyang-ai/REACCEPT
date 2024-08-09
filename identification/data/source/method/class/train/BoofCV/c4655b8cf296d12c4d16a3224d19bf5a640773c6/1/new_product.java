public void setTransform( int width , int height , PixelTransform_F32 transform ) {
		// sanity check since I think many people will screw this up.
		RectangleLength2D_F32 rect = DistortImageOps.boundBox_F32(width, height, transform);
		float x1 = rect.x0 + rect.width;
		float y1 = rect.y0 + rect.height;

		if( rect.getX() < 0 || rect.getY() < 0 || x1 > width || y1 > height ) {
			throw new IllegalArgumentException("You failed the idiot test! RTFM! The undistorted image "+
					"must be contained by the same bounds as the input distorted image");
		}

		InterpolatePixelS<T> interpolate = FactoryInterpolation.bilinearPixelS(imageType);
		integralImage = new GImageSingleBandDistorted<T>(transform,interpolate);
	}