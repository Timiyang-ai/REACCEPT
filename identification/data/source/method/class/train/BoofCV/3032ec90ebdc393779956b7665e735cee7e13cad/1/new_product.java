public static PointTransform_F32 transform_F32(AdjustmentType type,
												   IntrinsicParameters param,
												   IntrinsicParameters paramAdj,
												   boolean adjToDistorted)
	{
		PointTransform_F32 remove_p_to_p = distortTransform(param).undistort_F32(true, true);

		RectangleLength2D_F32 bound;
		if( type == AdjustmentType.FULL_VIEW ) {
			bound = DistortImageOps.boundBox_F32(param.width, param.height,
					new PointToPixelTransform_F32(remove_p_to_p));
		} else if( type == AdjustmentType.SHRINK) {
			bound = LensDistortionOps.boundBoxInside(param.width, param.height,
					new PointToPixelTransform_F32(remove_p_to_p));

			// ensure there are no strips of black
			LensDistortionOps.roundInside(bound);
		} else {
			throw new IllegalArgumentException("Unsupported type "+type);
		}

		double scaleX = bound.width/param.width;
		double scaleY = bound.height/param.height;

		double scale,deltaX,deltaY;

		if( type == AdjustmentType.FULL_VIEW ) {
			scale = Math.max(scaleX, scaleY);
			deltaX = bound.x0;
			deltaY = bound.y0;
		} else {
			scale = Math.min(scaleX, scaleY);
			// translation and shift over so that the small axis is in the middle
			deltaX = bound.x0 + (scaleX-scale)*param.width/2.0;
			deltaY = bound.y0 + (scaleY-scale)*param.height/2.0;
		}

		// adjustment matrix
		DenseMatrix64F A = new DenseMatrix64F(3,3,true,scale,0,deltaX,0,scale,deltaY,0,0,1);

		return adjustmentTransform_F32(param, paramAdj, adjToDistorted, remove_p_to_p, A);
	}