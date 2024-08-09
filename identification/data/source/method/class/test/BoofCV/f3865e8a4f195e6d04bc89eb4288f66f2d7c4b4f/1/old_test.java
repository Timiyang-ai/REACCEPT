@Test
	public void compareToNaive() {
		ImageUInt8 img = new ImageUInt8(width, height);
		ImageTestingOps.randomize(img, new Random(0xfeed), 0, 100);

		ImageSInt16 derivX = new ImageSInt16(img.getWidth(), img.getHeight());
		ImageSInt16 derivY = new ImageSInt16(img.getWidth(), img.getHeight());

		GradientSobel.process(img, derivX, derivY, new ImageBorder1D_I32(BorderIndex1D_Extend.class));

		BoofTesting.checkSubImage(this, "compareToNaive", true, derivX, derivY);
	}