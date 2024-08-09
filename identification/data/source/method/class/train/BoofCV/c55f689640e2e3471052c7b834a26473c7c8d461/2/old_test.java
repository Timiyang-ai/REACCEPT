@Test
	public void computeRawDescriptor_scale() {
		ImageFloat32 derivX = new ImageFloat32(200,200);
		ImageFloat32 derivY = new ImageFloat32(200,200);

		DescribePointSiftLowe alg = new DescribePointSiftLowe(4,4,8,1.5,0.5,0.2);
		int r = alg.getCanonicalRadius();
		ImageMiscOps.fillRectangle(derivX,5.0f,60,60,2*r,2*r);

		alg.setImageGradient(derivX,derivY);
		alg.descriptor = new TupleDesc_F64(128);
		alg.computeRawDescriptor(60+r, 60+r, 1, 0);

		int numHit = computeInside(alg);
		assertEquals(4*4,numHit);

		alg.descriptor = new TupleDesc_F64(128);
		alg.computeRawDescriptor(60+r, 60+r, 2, 0);
		numHit = computeInside(alg);
		assertEquals(2*2,numHit);

	}