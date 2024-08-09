@Test
	public void scaleSpace() {
		double ss[] = new double[]{1,2,4,6,8,10};

		PyramidFloat<GrayF32> pyramid = FactoryPyramid.scaleSpacePyramid(ss, GrayF32.class);

		for( int i = 0; i < ss.length; i++ ) {
			assertEquals(ss[i],pyramid.getSigma(i),1e-8);
			assertEquals(ss[i],pyramid.getScale(i),1e-8);
			// the amount of blur applied to the previous layer should be different
			if( i > 1)
				assertTrue(Math.abs(ss[i] - ((PyramidFloatGaussianScale)pyramid).getSigmaLayers()[i])>0.1);
		}
	}