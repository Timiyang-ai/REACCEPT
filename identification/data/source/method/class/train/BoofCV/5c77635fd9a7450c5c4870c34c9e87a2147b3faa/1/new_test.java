@Test
	public void setOriginToCurrent() {
		HelperMotion motion = new HelperMotion();
		HelperDistort distort = new HelperDistort();

		StitchingTransform trans = FactoryStitchingTransform.createAffine_F64();

		StitchingFromMotion2D<ImageFloat32,Affine2D_F64> alg =
				new StitchingFromMotion2D<ImageFloat32,Affine2D_F64>(motion,distort,trans,0.3);

		alg.configure(200,300,null);
		assertTrue(alg.process(image));

		alg.setOriginToCurrent();

		assertEquals(2, distort.numSetModel);
		assertEquals(2, distort.numApply);
	}