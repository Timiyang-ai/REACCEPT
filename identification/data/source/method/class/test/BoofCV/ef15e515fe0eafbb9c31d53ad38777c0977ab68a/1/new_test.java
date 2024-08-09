@Test
	public void trilinearInterpolation() {
		DescribePointSiftLowe alg = new DescribePointSiftLowe(4,4,8,1.5,0.5,0.2);

		alg.descriptor = new TupleDesc_F64(128);

		// in the middle of the feature, the total amount added to the descriptor should equal the input weight
		// upper edges will have a value less than the input weight
		alg.trilinearInterpolation(2.0f,1.25f,2.0f,0.5);

		double sum = 0;
		int count = 0;
		for (int i = 0; i < alg.descriptor.size(); i++) {
			sum += alg.descriptor.value[i];
			if( alg.descriptor.value[i] != 0 )
				count++;
		}
		assertEquals(2.0,sum,1e-6);
		assertTrue(count>1);

		// try an edge case
		sum = 0;
		alg.descriptor.fill(0);
		alg.trilinearInterpolation(2.0f,3.25f,3.25f,0.5);
		for (int i = 0; i < alg.descriptor.size(); i++) {
			sum += alg.descriptor.value[i];
		}
		assertEquals( 2.0*0.75*0.75*1.0, sum, 1e-8 );

		// now have something exactly at the start of a bin.  all the weight should be in one location
		alg.descriptor.fill(0);
		alg.trilinearInterpolation(2.0f,3f,3f,2*Math.PI/8);
		count = 0;
		for (int i = 0; i < alg.descriptor.size(); i++) {
			double weight = alg.descriptor.value[i];
			if( weight > 0 ) {
				assertEquals(2.0,weight,1e-8);
				count++;
			}
		}
		assertEquals(1,count);
	}