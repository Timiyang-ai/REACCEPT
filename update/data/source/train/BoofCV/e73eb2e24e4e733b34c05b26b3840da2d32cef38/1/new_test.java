@Test
	public void derivY_I8() {
		ImageInt8 img = new ImageInt8(width, height);
		BasicDrawing_I8.randomize(img, rand);

		ImageInt16 derivY = new ImageInt16(width, height, true);

		GecvTesting.checkSubImage(this, "derivY_I8", true, img, derivY);
	}