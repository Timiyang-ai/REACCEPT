@Test
	public void naive8_equal() {
		GrayF32 intensity = new GrayF32(3,3);
		GrayF32 output = new GrayF32(3,3);
		GrayS8 direction = new GrayS8(3,3);

		GImageMiscOps.fill(intensity, 2);

		ImplEdgeNonMaxSuppression.naive8(intensity, direction, output);

		for( int i = 0; i < output.data.length; i++ )
			assertEquals(2,output.data[i],1e-4f);
	}