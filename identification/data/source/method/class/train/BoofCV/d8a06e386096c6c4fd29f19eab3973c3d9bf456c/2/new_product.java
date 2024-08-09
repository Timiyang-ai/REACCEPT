public static PointTransform_F32 allInside( IntrinsicParameters param,
												IntrinsicParameters paramAdj ) {
		RemoveRadialPtoP_F32 removeDistort = new RemoveRadialPtoP_F32();
		AddRadialPtoP_F32 addDistort = new AddRadialPtoP_F32();
		removeDistort.set(param.fx, param.fy, param.skew, param.cx, param.cy, param.radial);
		addDistort.set(param.fx, param.fy, param.skew, param.cx, param.cy, param.radial);

		Rectangle2D_F32 bound = LensDistortionOps.boundBoxInside(param.width, param.height,
				new PointToPixelTransform_F32(removeDistort));

		double scaleX = bound.width/param.width;
		double scaleY = bound.height/param.height;

		double scale = Math.min(scaleX, scaleY);

		// translation and shift over so that the small axis is in the middle
		double deltaX = bound.tl_x + (scaleX-scale)*param.width/2.0;
		double deltaY = bound.tl_y + (scaleY-scale)*param.height/2.0;

		// adjustment matrix
		DenseMatrix64F A = new DenseMatrix64F(3,3,true,scale,0,deltaX,0,scale,deltaY,0,0,1);

		PointTransform_F32 tranAdj = UtilIntrinsic.adjustIntrinsic_F32(addDistort, false, param, A, paramAdj);

		if( param.leftHanded) {
			PointTransform_F32 l2r = new LeftToRightHanded_F32(param.height);
			return new SequencePointTransform_F32(l2r,tranAdj,l2r);
		} else
			return tranAdj;
	}