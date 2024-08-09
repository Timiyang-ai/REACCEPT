	@Test
	public void alphaOffset()
	{
		// alphaOffset(BufferedImage image, int offset)
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK_TRANSPARENT), -255)));
		assertTrue(bufferedImagesEqual(oneByOne(new Color(0, 0, 0, 50)), ImageUtil.alphaOffset(oneByOne(BLACK_TRANSPARENT), 50)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_HALF_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK_TRANSPARENT), 128)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK_HALF_TRANSPARENT), -255)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.alphaOffset(oneByOne(BLACK_TRANSPARENT), 255)));
		assertTrue(bufferedImagesEqual(oneByOne(new Color(0, 0, 0, 200)), ImageUtil.alphaOffset(oneByOne(BLACK), -55)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.alphaOffset(oneByOne(BLACK), 255)));

		// alphaOffset(BufferedImage image, float offset)
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK_TRANSPARENT), 0f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK_TRANSPARENT), 1f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK_TRANSPARENT), 2f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK_HALF_TRANSPARENT), 0f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_HALF_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK_HALF_TRANSPARENT), 1f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.alphaOffset(oneByOne(BLACK_HALF_TRANSPARENT), 2f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK), 0f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_HALF_TRANSPARENT), ImageUtil.alphaOffset(oneByOne(BLACK), 0.503f))); // opacityOffset does Math.floor
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.alphaOffset(oneByOne(BLACK), 1f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.alphaOffset(oneByOne(BLACK), 2f)));
	}