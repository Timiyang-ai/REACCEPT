@Test
	public void process() {
		ImageFloat32 derivX = new ImageFloat32(200,200);
		ImageFloat32 derivY = new ImageFloat32(200,200);

		GImageMiscOps.fillUniform(derivX,rand,-100,100);
		GImageMiscOps.fillUniform(derivY,rand,-100,100);

		DescribePointSift<ImageFloat32> alg =
				new DescribePointSift<ImageFloat32>(4,4,8,1.5,0.5,0.2,ImageFloat32.class);
		alg.setImageGradient(derivX,derivY);

		List<Point2D_I32> testPoints = new ArrayList<Point2D_I32>();
		testPoints.add( new Point2D_I32(100,0));
		testPoints.add( new Point2D_I32(100,199));
		testPoints.add( new Point2D_I32(0,100));
		testPoints.add( new Point2D_I32(199,100));
		testPoints.add( new Point2D_I32(100,100));

		TupleDesc_F64 desc = new TupleDesc_F64(alg.getDescriptorLength());
		for( Point2D_I32 where : testPoints ) {
			alg.process(where.x,where.y,2,0.5,desc);
		}
	}