@Test
	public void fullView() {
		IntrinsicParameters param = new IntrinsicParameters(false).
				fsetMatrix(300, 320, 0, 150, 130, width, height).fsetRadial(0.1,1e-4);

		PointTransform_F32 adjusted = LensDistortionOps.fullView(param,null);

		checkBorderOutside(adjusted);
	}