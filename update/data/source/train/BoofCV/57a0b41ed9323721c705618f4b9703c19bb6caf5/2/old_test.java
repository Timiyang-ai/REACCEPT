@Test
	public void vertical() {
		Kernel1D_I32 kernel = new Kernel1D_I32(new int[]{1,2,3,4,5,6}, 6, 4);

		ImageUInt8 input = new ImageUInt8(15,16);
		ImageMiscOps.fillUniform(input, rand, 0, 50);
		ImageUInt8 output = new ImageUInt8(15,16);

		ConvolveNormalizedNaive.vertical(kernel, input, output);

		for (int y = 0; y < output.height; y++) {
			for (int x = 0; x < output.width; x++) {
				int expected = vertical(x, y, kernel, input);
				int found = output.get(x,y);
				assertEquals(x+"  "+y,expected,found);
			}
		}
	}