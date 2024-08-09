@Test
	public void process() {
		DummyFeature sparse = new DummyFeature();

		GenericDenseDescribeImageDense alg = new GenericDenseDescribeImageDense(sparse);
		alg.configure(1.5,3,4);

		ImageUInt8 image = new ImageUInt8(100,110);

		alg.process(image);
		List<TupleDesc_F64> descs = alg.getDescriptions();
		List<Point2D_I32> points = alg.getLocations();

		assertEquals(descs.size(),points.size());

		int featureRadius = (int)Math.round(1.5*7.0/2.0);
		int w = (100-2*featureRadius)/3;
		int h = (110-2*featureRadius)/4;

		// -1 since it intentionally skips feature 20
		assertEquals(w*h-1,points.size());

		int count = 0;
		for (int y = 0; y < h; y++) {
			int pixelY = featureRadius + y*y;
			for (int x = 0; x < w; x++) {
				int pixelX = featureRadius + x*3;

				Point2D_I32 p = null;
				if( count < 19 ) {
					p = points.get(count);
				} else if( count > 20 ) {
					p = points.get(count+1);
				} else {
					continue;
				}
				assertEquals("count = "+count,pixelX,p.x);
				assertEquals(pixelY,p.y);
				count++;
			}
		}
	}