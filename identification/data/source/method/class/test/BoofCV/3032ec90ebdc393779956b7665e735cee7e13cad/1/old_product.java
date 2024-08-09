public static PointTransform_F32 fullView( IntrinsicParameters param,
											   IntrinsicParameters paramAdj ,
											   boolean adjToDistorted ) {

		PointTransform_F32 remove_p_to_p = distortTransform(param).undistort_F32(true, true);

		RectangleLength2D_F32 bound = DistortImageOps.boundBox_F32(param.width, param.height,
				new PointToPixelTransform_F32(remove_p_to_p));

		double scaleX = bound.width/param.width;
		double scaleY = bound.height/param.height;

		double scale = Math.max(scaleX, scaleY);

		// translation
		double deltaX = bound.x0;
		double deltaY = bound.y0;

		// adjustment matrix
		DenseMatrix64F A = new DenseMatrix64F(3,3,true,scale,0,deltaX,0,scale,deltaY,0,0,1);

		return adjustmentTransform(param, paramAdj, adjToDistorted, remove_p_to_p, A);
	}