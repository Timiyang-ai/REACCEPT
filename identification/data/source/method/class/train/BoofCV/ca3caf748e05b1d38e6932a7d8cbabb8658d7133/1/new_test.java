@Test
	public void estimatePinhole() {
		CameraPinhole expected = new CameraPinhole(500,550,0,600,700,1200,1400);
		Point2Transform2_F64 pixelToNorm = new LensDistortionPinhole(expected).distort_F64(true,false);

		CameraPinhole found = PerspectiveOps.estimatePinhole(pixelToNorm,expected.width,expected.height);

		assertEquals(expected.fx,found.fx,UtilEjml.TEST_F64);
		assertEquals(expected.fy,found.fy,UtilEjml.TEST_F64);
		assertEquals(expected.cx,found.cx,UtilEjml.TEST_F64);
		assertEquals(expected.cy,found.cy,UtilEjml.TEST_F64);
		assertEquals(expected.skew,found.skew,UtilEjml.TEST_F64);
		assertEquals(expected.width,found.width);
		assertEquals(expected.height,found.height);
	}