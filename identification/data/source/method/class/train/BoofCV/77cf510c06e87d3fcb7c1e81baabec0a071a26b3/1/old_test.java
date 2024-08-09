@Test
	public void derivX_I8() {
		ImageInt8 img = new ImageInt8(width, height);
		BasicDrawing_I8.randomize(img, rand);

		ImageInt16 derivX = new ImageInt16(width, height, true);

		GecvTesting.checkSubImage(this, "derivX_I8", true, img, derivX);
	}