public static void fullViewLeft(IntrinsicParameters paramLeft,
									DenseMatrix64F rectifyLeft, DenseMatrix64F rectifyRight,
									DenseMatrix64F rectifyK)
	{
		PointTransform_F32 tranLeft = rectifyTransform(paramLeft, rectifyLeft);

		Rectangle2D_F32 bound = DistortImageOps.boundBox_F32(paramLeft.width, paramLeft.height,
				new PointToPixelTransform_F32(tranLeft));

		double scaleX = paramLeft.width/bound.width;
		double scaleY = paramLeft.height/bound.height;

		double scale = Math.min(scaleX, scaleY);

		adjustCalibrated(rectifyLeft, rectifyRight, rectifyK, bound, scale);
	}