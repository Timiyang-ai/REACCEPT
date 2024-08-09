@Test
	public void naive4_equal() {
		ImageFloat32 intensity = new ImageFloat32(3,3);
		ImageFloat32 output = new ImageFloat32(3,3);
		ImageSInt8 direction = new ImageSInt8(3,3);

		GImageMiscOps.fill(intensity, 2);

		ImplEdgeNonMaxSuppression.naive4(intensity,direction,output);

		for( int i = 0; i < output.data.length; i++ )
			assertEquals(2,output.data[i],1e-4f);
	}