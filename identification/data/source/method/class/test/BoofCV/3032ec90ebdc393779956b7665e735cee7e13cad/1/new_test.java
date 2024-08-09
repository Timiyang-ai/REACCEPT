@Test
	public void transform_F32_fullView() {
		IntrinsicParameters param = new IntrinsicParameters().
				fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1, 0.05);

		PointTransform_F32 adjToDist = LensDistortionOps.transform_F32(AdjustmentType.FULL_VIEW, param, null, true);
		PointTransform_F32 distToAdj = LensDistortionOps.transform_F32(AdjustmentType.FULL_VIEW, param, null, false);

		checkBorderOutside(adjToDist,distToAdj);

		param = new IntrinsicParameters().
				fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(-0.1,-0.05);
		adjToDist = LensDistortionOps.transform_F32(AdjustmentType.FULL_VIEW, param, null, true);
		distToAdj = LensDistortionOps.transform_F32(AdjustmentType.FULL_VIEW, param, null, false);
		checkBorderOutside(adjToDist,distToAdj);
	}