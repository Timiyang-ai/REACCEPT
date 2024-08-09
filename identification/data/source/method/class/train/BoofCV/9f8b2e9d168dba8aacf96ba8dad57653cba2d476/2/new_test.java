@Test
	public void allInside() {
		IntrinsicParameters param = new IntrinsicParameters().fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1,1e-4);

		PointTransform_F32 adjusted = LensDistortionOps.allInside(param,null,true);
		checkInside(adjusted);

		// distort it in the other direction
		param = new IntrinsicParameters().fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(-0.1,-1e-4);

		adjusted = LensDistortionOps.allInside(param, null,true);
		checkInside(adjusted);
	}