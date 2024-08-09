@Test
	public void contour() {
		ImageUInt8 input = new ImageUInt8(10,12);
		ImageMiscOps.fillRectangle(input,1,2,3,4,5);
		input.set(9,11,1);

		ImageSInt32 output = new ImageSInt32(10,12);
		ImageSInt32 expected = new ImageSInt32(10,12);
		ImageMiscOps.fillRectangle(expected,1,2,3,4,5);
		expected.set(9,11,2);

		List<Contour> found = BinaryImageOps.contour(input, ConnectRule.FOUR,output);

		assertEquals(2,found.size());
		BoofTesting.assertEquals(expected,output,0);
	}