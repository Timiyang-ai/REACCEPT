@Test
	public void deriv_I8() {
		ImageInt8 img = new ImageInt8(width, height);
		UtilImageInt8.randomize(img, rand);

		ImageInt16 derivX = new ImageInt16(width, height);
		ImageInt16 derivY = new ImageInt16(width, height);

		GecvTesting.checkSubImage(this, "deriv_I8", true, img, derivX, derivY);
	}