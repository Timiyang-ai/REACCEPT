@Test
	public void process() {
		ImageUInt8 image = new ImageUInt8(20,25);
		ImageMiscOps.fillRectangle(image,100,0,0,10,25);
		ImageSInt32 output = new ImageSInt32(20,25);

		// normal images
		process(image, output);

		// sub-images
		process(BoofTesting.createSubImageOf(image), output);
	}