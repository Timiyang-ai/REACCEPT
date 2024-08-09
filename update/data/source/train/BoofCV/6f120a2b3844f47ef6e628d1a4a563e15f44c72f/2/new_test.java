@Test
	public void computeDescriptor() {
		ImageFloat32 derivX = new ImageFloat32(100,102);
		ImageFloat32 derivY = new ImageFloat32(100,102);

		GImageMiscOps.fillUniform(derivX,rand,0,200);
		GImageMiscOps.fillUniform(derivY,rand,0,200);

		DescribeDenseSift<ImageFloat32> alg = new DescribeDenseSift<ImageFloat32>(4,4,8,0.5,0.2,10,10,ImageFloat32.class);
		DescribePointSift<ImageFloat32> algTest = new DescribePointSift<ImageFloat32>(4,4,8,1,0.5,0.2,ImageFloat32.class);

		alg.setImageGradient(derivX,derivY);
		algTest.setImageGradient(derivX,derivY);

		List<Point2D_I32> samplePoints = new ArrayList<Point2D_I32>();
		samplePoints.add( new Point2D_I32(30,35));
		samplePoints.add( new Point2D_I32(45,10));
		samplePoints.add( new Point2D_I32(60,12));
		samplePoints.add( new Point2D_I32(50,50));

		TupleDesc_F64 found = new TupleDesc_F64(128);
		TupleDesc_F64 expected = new TupleDesc_F64(128);

		for( Point2D_I32 p : samplePoints ) {
			alg.computeDescriptor(p.x,p.y,found);
			algTest.process(p.x,p.y,1,0,expected);

			for (int i = 0; i < 128; i++) {
				assertEquals(expected.value[i],found.value[i],1e-8);
			}
		}

	}