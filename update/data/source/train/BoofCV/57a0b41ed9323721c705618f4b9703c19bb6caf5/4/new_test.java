@Test
	public void contour() {
		GrayU8 input = new GrayU8(10,12);
		ImageMiscOps.fillRectangle(input,1,2,3,4,5);
		input.set(9,11,1);

		GrayS32 output = new GrayS32(10,12);
		GrayS32 expected = new GrayS32(10,12);
		ImageMiscOps.fillRectangle(expected,1,2,3,4,5);
		expected.set(9,11,2);

		List<Contour> found = BinaryImageOps.contour(input, ConnectRule.FOUR,output);

		assertEquals(2,found.size());
		BoofTesting.assertEquals(expected,output,0);
	}