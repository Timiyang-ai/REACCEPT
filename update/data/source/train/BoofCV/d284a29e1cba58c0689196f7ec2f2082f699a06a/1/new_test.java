@Test
	public void computeLocalGradient() {
		SubpixelSparseCornerFit<ImageUInt8> alg = new SubpixelSparseCornerFit<ImageUInt8>(ImageUInt8.class);

		ImageUInt8 input = new ImageUInt8(20,25);
		ImageMiscOps.fillUniform(input,rand,0,200);

		alg.setIgnoreRadius(1);
		alg.setLocalRadius(4);
		alg.setImage(input);

		alg.computeLocalGradient(10,11);
		assertEquals((9*9-3*3),alg.points.size());

		// all coordinates should range from -1 to 1, after normalization
		for (int i = 0; i < alg.points.size; i++) {
			PointGradient_F64 p = alg.points.get(i);

			assertTrue(-1<=p.x && 1>=p.x);
			assertTrue(-1<=p.y && 1>=p.y);
		}

		// test border handling
		alg.points.reset();
		alg.computeLocalGradient(0,0);
		assertEquals((5*5-2*2),alg.points.size());
	}