@Test
	public void fullView_Transform() {
		IntrinsicParameters param = new IntrinsicParameters().
				fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1, 0.05);

		PointTransform_F32 adjToDist = LensDistortionOps.fullView(param, null, true);
		PointTransform_F32 distToAdj = LensDistortionOps.fullView(param, null, false);

		checkBorderOutside(adjToDist,distToAdj);

		param = new IntrinsicParameters().
				fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(-0.1,-0.05);
		adjToDist = LensDistortionOps.fullView(param, null, true);
		distToAdj = LensDistortionOps.fullView(param, null, false);
		checkBorderOutside(adjToDist,distToAdj);
	}