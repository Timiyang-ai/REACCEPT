@Test
	public void fullView() {
		IntrinsicParameters param = new IntrinsicParameters().
				fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1, 0.05);

		PointTransform_F32 adjusted = LensDistortionOps.fullView(param,null);
		checkBorderOutside(adjusted);

		param = new IntrinsicParameters().
				fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(-0.1,-0.05);
		adjusted = LensDistortionOps.fullView(param,null);
		checkBorderOutside(adjusted);

	}