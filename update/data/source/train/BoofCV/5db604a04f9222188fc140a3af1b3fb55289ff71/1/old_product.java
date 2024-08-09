public static PointTransform_F64 rectifyNormalized_F64(IntrinsicParameters param,
														   DenseMatrix64F rectify ,
														   DenseMatrix64F rectifyK )
	{
		if( rectifyK.get(0,1) != 0 )
			throw new IllegalArgumentException("Skew should be zero in rectified images");

		RemoveRadialPtoP_F64 radialDistort = new RemoveRadialPtoP_F64();
		radialDistort.set(param.fx, param.fy, param.skew, param.cx, param.cy, param.radial);

		PointTransformHomography_F64 rectifyDistort = new PointTransformHomography_F64(rectify);

		PixelToNormalized_F64 pixelToNorm = new PixelToNormalized_F64();
		pixelToNorm.set(rectifyK.get(0,0),rectifyK.get(1,1),
				rectifyK.get(0,1),
				rectifyK.get(0,2),rectifyK.get(1,2));

		if( param.flipY) {
			FlipVertical_F64 flip = new FlipVertical_F64(param.height);
			return new SequencePointTransform_F64(flip,radialDistort,rectifyDistort,pixelToNorm);
		} else {
			return new SequencePointTransform_F64(radialDistort,rectifyDistort,pixelToNorm);
		}
	}