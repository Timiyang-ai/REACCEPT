@Test
	public void rotate_SanityCheck() {
		ImageFloat32 input = new ImageFloat32(width,height);
		ImageFloat32 output = new ImageFloat32(height,width);

		GeneralizedImageOps.randomize(input,rand,0,100);

		DistortImageOps.rotate(input, output, TypeInterpolate.BILINEAR, (float) Math.PI / 2f);

		double error = 0;
		// the outside pixels are ignored because numerical round off can cause those to be skipped
		for( int y = 1; y < input.height-1; y++ ) {
			for( int x = 1; x < input.width-1; x++ ) {
				int xx = output.width-y;
				int yy = x;

				double e = input.get(x,y)-output.get(xx,yy);
				error += Math.abs(e);
			}
		}
		assertTrue(error / (width * height) < 0.1);
	}