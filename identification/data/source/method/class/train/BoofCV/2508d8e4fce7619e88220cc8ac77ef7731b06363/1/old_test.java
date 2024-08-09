@Test
	public void equalizeLocalNaive() {
		ImageUInt8 input = new ImageUInt8(width,height);
		ImageUInt8 output = new ImageUInt8(width,height);
		ImageUInt8 tmp = new ImageUInt8(width,height);
		int transform[] = new int[10];
		int histogram[] = new int[10];

		ImageMiscOps.fillUniform(input,rand,0,10);

		for( int radius = 1; radius < 11; radius++ ) {
			ImplEnhanceHistogram.equalizeLocalNaive(input,radius,output,histogram);

			int width = 2*radius+1;

			for( int y = 0; y < height; y++ ) {
				int y0 = y - radius;
				int y1 = y + radius+1;
				if( y0 < 0 ) {
					y0 = 0; y1 = y0 +width;
					if( y1 > input.height )  y1 = input.height;
				} else if( y1 > input.height ) {
					y1 = input.height; y0 = y1 - width;
					if( y0 < 0 ) y0 = 0;
				}
				for( int x = 0; x < input.width; x++ ) {
					int x0 = x - radius;
					int x1 = x + radius+1;
					if( x0 < 0 ) {
						x0 = 0; x1 = x0 + width;
						if( x1 > input.width ) x1 = input.width;
					} else if( x1 > input.width ) {
						x1 = input.width; x0 = x1 - width;
						if( x0 < 0 ) x0 = 0;
					}

					// use the full image algorithm
					ImageUInt8 subIn = input.subimage(x0,y0,x1,y1);
					ImageUInt8 subOut = tmp.subimage(x0,y0,x1,y1);
					ImageStatistics.histogram(subIn,histogram);
					EnhanceImageOps.equalize(histogram, transform);
					EnhanceImageOps.applyTransform(subIn, transform, subOut);

					int expected = subOut.get(x-x0,y-y0);
					int found = output.get(x,y);

					assertEquals(x+" "+y,expected,found);
				}
			}
		}
	}