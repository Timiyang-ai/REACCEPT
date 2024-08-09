@Test
	public void horizontal() {
		Kernel1D_S32 kernel = new Kernel1D_S32(new int[]{1,2,3,4,5,6}, 6, 4);

		GrayU8 input = new GrayU8(15,16);
		ImageMiscOps.fillUniform(input, rand, 0, 50);
		GrayU8 output = new GrayU8(15,16);

		ConvolveNormalizedNaive.horizontal(kernel,input,output);

		for (int y = 0; y < output.height; y++) {
			for (int x = 0; x < output.width; x++) {
				int expected = horizontal(x,y,kernel,input);
				int found = output.get(x,y);
				assertEquals(x+"  "+y,expected,found);
			}
		}
	}