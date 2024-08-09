@Test
	public void derivX_I8() {
		ImageUInt8 img = new ImageUInt8(width, height);
		BasicDrawing_I8.randomize(img, rand);

		ImageSInt16 derivX = new ImageSInt16(width, height);

		GecvTesting.checkSubImage(this, "derivX_I8", true, img, derivX);
	}