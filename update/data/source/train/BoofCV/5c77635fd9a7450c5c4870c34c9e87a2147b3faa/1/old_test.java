@Test
	public void setOriginToCurrent() {
		HelperMotion motion = new HelperMotion();
		HelperDistort distort = new HelperDistort();

		StitchingTransform trans = FactoryStitchingTransform.createAffine_F64();

		StitchingFromMotion2D<ImageFloat32,Affine2D_F64> alg =
				new StitchingFromMotion2D<ImageFloat32,Affine2D_F64>(motion,distort,trans,0.3);

		alg.configure(200,300,null);
		assertTrue(alg.process(image));

		Affine2D_F64 found = alg.getWorldToCurr();
		assertEquals(1,found.tx,1e-5);
		assertEquals(-2,found.ty,1e-5);

		alg.setOriginToCurrent();

		// the image should not be at the initial location
		found = alg.getWorldToCurr();
		assertEquals(0,found.tx,1e-5);
		assertEquals(0,found.ty,1e-5);

		assertEquals(2, distort.numSetModel);
		assertEquals(2, distort.numApply);
	}