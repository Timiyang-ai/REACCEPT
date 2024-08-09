@Test
	public void features() {
		double scale = 1.5;
		double []found = new double[ 64 ];
		double []expected = new double[ 64 ];

		Kernel2D_F64 weight = FactoryKernelGaussian.gaussianWidth(-1, 20);
		SurfDescribeOps.features(inputF32,c_x,c_y,1.2,weight,4,5,2,scale,false,true,found);

		SparseImageGradient<ImageFloat32,?> gradient = SurfDescribeOps.createGradient(true,false,2,scale,ImageFloat32.class);
		gradient.setImage(inputF32);
		ImplSurfDescribeOps.features(c_x,c_y,1.2,weight,4,5,scale,gradient,expected);

		BoofTesting.assertEquals(expected,found,1e-4);
	}