@Test
	public void allInside() {
		IntrinsicParameters param = new IntrinsicParameters(300,320,0,150,130,
				width,height,new double[]{0.1,1e-4});

		PointTransform_F32 adjusted = LensDistortionOps.allInside(param,false,null);

		checkInside(adjusted);
	}