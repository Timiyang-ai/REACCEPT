public static void fullViewLeft(int imageWidth,int imageHeight,
									DenseMatrix64F rectifyLeft, DenseMatrix64F rectifyRight )
	{
		Point2Transform2_F32 tranLeft = new PointTransformHomography_F32(rectifyLeft);

		RectangleLength2D_F32 bound = DistortImageOps.boundBox_F32(imageWidth, imageHeight,
				new PointToPixelTransform_F32(tranLeft));

		double scaleX = imageWidth/bound.width;
		double scaleY = imageHeight/bound.height;

		double scale = Math.min(scaleX,scaleY);

		adjustUncalibrated(rectifyLeft, rectifyRight, bound, scale);
	}