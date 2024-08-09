@Test
	public void setTransform_input() {
		Affine2D_F32 a = new Affine2D_F32();
		transform.set(1.2,0,0,1.2,0,0);
		UtilAffine.convert(transform,a);

		PixelTransform2_F32 tranFrom = new PixelTransformAffine_F32(a);
		PixelTransform2_F32 tranTo = new PixelTransformAffine_F32(a.invert(null));

		BinaryPolygonDetector alg = createDetector(GrayU8.class, true, 4,4);
		alg.setLensDistortion(width, height, tranTo, tranFrom);
		try {
			// this should cause it to go outside the image
			alg.setLensDistortion(width, height, tranFrom, tranTo);
			fail("didn't blow up");
		} catch( IllegalArgumentException ignore ){}
	}