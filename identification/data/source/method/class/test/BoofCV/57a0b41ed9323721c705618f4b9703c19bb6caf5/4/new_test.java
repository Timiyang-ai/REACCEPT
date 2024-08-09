@Test
	public void hessian_F32() {
		GrayF32 original = new GrayF32(width,height);
		GrayF32 integral = new GrayF32(width,height);
		GrayF32 found = new GrayF32(width,height);
		GrayF32 expected = new GrayF32(width,height);

		GImageMiscOps.fillUniform(original, rand, 0, 50);
		IntegralImageOps.transform(original,integral);

		int size = 9;

		for( int skip = 1; skip <= 4; skip++ ) {
			found.reshape(width/skip,height/skip);
			expected.reshape(width/skip,height/skip);
			ImplIntegralImageFeatureIntensity.hessianNaive(integral,skip,size,expected);
			IntegralImageFeatureIntensity.hessian(integral,skip,size,found);

			BoofTesting.assertEquals(expected,found, 1e-4f);
		}
	}