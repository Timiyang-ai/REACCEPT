@Test
	public void process() {
		DummyFeature sparse = new DummyFeature();

		GenericDenseDescribeImageDense alg = new GenericDenseDescribeImageDense(sparse,1.5,7,3,4);

		FastQueue<TupleDesc_F64> descs = new FastQueue<TupleDesc_F64>(TupleDesc_F64.class,true) {
			@Override
			protected TupleDesc_F64 createInstance() {
				return new TupleDesc_F64(5);
			}
		};
		FastQueue<Point2D_I32> points = new FastQueue<Point2D_I32>(Point2D_I32.class,true);

		ImageUInt8 image = new ImageUInt8(100,110);

		alg.process(image,descs,points);

		int w = (100-3)/3;
		int h = (110-3)/4;

		// -1 since it intentionally skips feature 20
		assertEquals(w*h-1,points.size);

		int count = 0;
		for (int y = 0; y < h; y++) {
			int pixelY = 7/2 + y*y;
			for (int x = 0; x < w; x++) {
				int pixelX = 7/2 + x*3;

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