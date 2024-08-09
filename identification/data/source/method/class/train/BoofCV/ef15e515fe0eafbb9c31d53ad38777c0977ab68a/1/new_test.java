@Test
	public void computeRawDescriptor_rotation() {
		ImageFloat32 derivX = new ImageFloat32(60,55);
		ImageFloat32 derivY = new ImageFloat32(60,55);

		ImageMiscOps.fill(derivX,5.0f);
		
		DescribePointSiftLowe alg = new DescribePointSiftLowe(4,4,8,1.5,0.5,0.2);
		alg.setImageGradient(derivX,derivY);

		for( int i = 0; i < 8; i++ ) {
			double angle = UtilAngle.bound(i*Math.PI/4);
			alg.descriptor = new TupleDesc_F64(128);
			alg.computeRawDescriptor(20, 21, 1, angle);

			int bin = (int) (UtilAngle.domain2PI(-angle) * 8 / (2 * Math.PI));
			for (int j = 0; j < 128; j++) {
				if (j % 8 == bin) {
					assertTrue(alg.descriptor.value[j] > 0);
				} else {
					assertEquals(0, alg.descriptor.value[j], 1e-4);
				}
			}
		}
		
	}