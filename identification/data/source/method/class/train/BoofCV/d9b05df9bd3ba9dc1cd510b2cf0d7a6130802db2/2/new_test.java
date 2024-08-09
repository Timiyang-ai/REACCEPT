@Test
	public void convolve() {
		Kernel2D_S32 kernel = FactoryKernel.random2D_I32(7,3,0,20,rand);
		kernel.offset = 1;

		GrayU8 input = new GrayU8(15,16);
		ImageMiscOps.fillUniform(input, rand, 0, 50);
		GrayU8 output = new GrayU8(15,16);

		ConvolveNormalizedNaive.convolve(kernel, input, output);

		for (int y = 0; y < output.height; y++) {
			for (int x = 0; x < output.width; x++) {
				int expected = convolve(x, y, kernel, input);
				int found = output.get(x,y);
				assertEquals(x+"  "+y,expected,found);
			}
		}
	}