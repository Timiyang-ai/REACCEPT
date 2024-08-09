@Test
	public void allInside_Transform() {
		IntrinsicParameters param =
				new IntrinsicParameters().fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1, 1e-4);

		PointTransform_F32 adjToDist = LensDistortionOps.allInside(param, null, true);
		PointTransform_F32 distToAdj = LensDistortionOps.allInside(param, null, false);
		checkInside(adjToDist, distToAdj);

		// distort it in the other direction
		param = new IntrinsicParameters().fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(-0.1,-1e-4);

		adjToDist = LensDistortionOps.allInside(param, null, true);
		distToAdj = LensDistortionOps.allInside(param, null, false);

		checkInside(adjToDist, distToAdj);
	}