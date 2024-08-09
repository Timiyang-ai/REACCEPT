public static void fullViewLeft(CameraPinholeRadial paramLeft,
									DenseMatrix64F rectifyLeft, DenseMatrix64F rectifyRight,
									DenseMatrix64F rectifyK)
	{
		// need to take in account the order in which image distort will remove rectification later on
		paramLeft = new CameraPinholeRadial(paramLeft);

		Point2Transform2_F32 tranLeft = transformPixelToRect_F32(paramLeft, rectifyLeft);

		RectangleLength2D_F32 bound = DistortImageOps.boundBox_F32(paramLeft.width, paramLeft.height,
				new PointToPixelTransform_F32(tranLeft));

		double scaleX = paramLeft.width/bound.width;
		double scaleY = paramLeft.height/bound.height;

		double scale = Math.min(scaleX, scaleY);

		adjustCalibrated(rectifyLeft, rectifyRight, rectifyK, bound, scale);
	}