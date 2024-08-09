@Test
	public void process() {
		GrayU8 image = new GrayU8(20,25);
		ImageMiscOps.fillRectangle(image,100,0,0,10,25);
		GrayS32 output = new GrayS32(20,25);

		// normal images
		process(image, output);

		// sub-images
		process(BoofTesting.createSubImageOf(image), output);
	}