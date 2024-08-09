@Test
	public void setTransform_input() {
		PointTransformHomography_F32 H = new PointTransformHomography_F32();
		PixelTransform_F32 transform = new PointToPixelTransform_F32(H);

		RefinePolygonLineToImage alg = createAlg(4,true, ImageUInt8.class);

		// correct example
		alg.setTransform(20,30,transform);

		// bad example
		try {
			H.getModel().a11 = 2;
			alg.setTransform(20,30,transform);
			fail("Should have thrown exception");
		} catch( RuntimeException ignore ) {}
	}