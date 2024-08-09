	@Test
	public void grayscaleOffset()
	{
		// grayscaleOffset(BufferedImage image, int offset)
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.luminanceOffset(oneByOne(BLACK), -255)));
		assertTrue(bufferedImagesEqual(oneByOne(new Color(50, 50, 50)), ImageUtil.luminanceOffset(oneByOne(BLACK), 50)));
		assertTrue(bufferedImagesEqual(oneByOne(GRAY), ImageUtil.luminanceOffset(oneByOne(BLACK), 128)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.luminanceOffset(oneByOne(GRAY), -255)));
		assertTrue(bufferedImagesEqual(oneByOne(WHITE), ImageUtil.luminanceOffset(oneByOne(BLACK), 255)));
		assertTrue(bufferedImagesEqual(oneByOne(new Color(200, 200, 200)), ImageUtil.luminanceOffset(oneByOne(WHITE), -55)));
		assertTrue(bufferedImagesEqual(oneByOne(WHITE), ImageUtil.luminanceOffset(oneByOne(WHITE), 55)));

		// grayscaleOffset(BufferedImage image, float percentage)
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.luminanceScale(oneByOne(BLACK), 0f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.luminanceScale(oneByOne(BLACK), 1f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.luminanceScale(oneByOne(BLACK), 2f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.luminanceScale(oneByOne(GRAY), 0f)));
		assertTrue(bufferedImagesEqual(oneByOne(GRAY), ImageUtil.luminanceScale(oneByOne(GRAY), 1f)));
		assertTrue(bufferedImagesEqual(oneByOne(WHITE), ImageUtil.luminanceScale(oneByOne(GRAY), 2f)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK), ImageUtil.luminanceScale(oneByOne(WHITE), 0f)));
		assertTrue(bufferedImagesEqual(oneByOne(GRAY), ImageUtil.luminanceScale(oneByOne(WHITE), 0.503f))); // grayscaleOffset does Math.floor
		assertTrue(bufferedImagesEqual(oneByOne(WHITE), ImageUtil.luminanceScale(oneByOne(WHITE), 1f)));
		assertTrue(bufferedImagesEqual(oneByOne(WHITE), ImageUtil.luminanceScale(oneByOne(WHITE), 2f)));
	}