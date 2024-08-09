public static PointTransform_F64 transformPixelToRectNorm_F64(CameraPinholeRadial param,
																  DenseMatrix64F rectify,
																  DenseMatrix64F rectifyK) {
		if (rectifyK.get(0, 1) != 0)
			throw new IllegalArgumentException("Skew should be zero in rectified images");

		PointTransform_F64 remove_p_to_p = transformPoint(param).undistort_F64(true, true);

		PointTransformHomography_F64 rectifyDistort = new PointTransformHomography_F64(rectify);

		PixelToNormalized_F64 pixelToNorm = new PixelToNormalized_F64();
		pixelToNorm.set(rectifyK.get(0, 0), rectifyK.get(1, 1),
				rectifyK.get(0, 1),
				rectifyK.get(0, 2), rectifyK.get(1, 2));

		return new SequencePointTransform_F64(remove_p_to_p, rectifyDistort, pixelToNorm);
	}