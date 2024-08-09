@Test
	public void selectPyramidScale() {
		int minSize = (5*2+1)*5;
		int[] scales = TldTracker.selectPyramidScale(640,480,minSize);

		assertTrue(scales.length > 3);

		for( int i = 0; i < scales.length; i++ ) {
			int w = 640/scales[i];
			int h = 6480/scales[i];

			assertTrue(w>minSize);
			assertTrue(h>minSize);
		}
	}