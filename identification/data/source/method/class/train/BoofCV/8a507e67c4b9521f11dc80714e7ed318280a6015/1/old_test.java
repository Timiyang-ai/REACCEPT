@Test
	public void fit_subimage() {
		final boolean black = true;

		Polygon2D_F64 input = new Polygon2D_F64(x0,y0 , x0,y1, x1,y1, x1,y0);

		for (Class imageType : imageTypes) {
			setup(new Affine2D_F64(), black, imageType);

			RefinePolygonLineToImage alg = createAlg(input.size(),black, imageType);

			Polygon2D_F64 output = new Polygon2D_F64(4);
			alg.initialize(image);
			assertTrue(alg.refine(input, output));

			// do it again with a sub-image
			Polygon2D_F64 output2 = new Polygon2D_F64(4);
			image = BoofTesting.createSubImageOf_S(image);
			alg.initialize(image);
			assertTrue(alg.refine(input, output2));

			assertTrue(UtilPolygons2D_F64.isIdentical(output, output2, 1e-8));
		}
	}