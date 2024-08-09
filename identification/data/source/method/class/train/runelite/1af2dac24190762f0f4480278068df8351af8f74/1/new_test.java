	@Test
	public void fillImage()
	{
		// fillImage(BufferedImage image, Color color)
		assertTrue(bufferedImagesEqual(centeredPixel(GRAY), ImageUtil.fillImage(centeredPixel(BLACK), GRAY)));
		assertTrue(bufferedImagesEqual(solidColor(3, 3, GREEN), ImageUtil.fillImage(solidColor(3, 3, BLACK), GREEN)));
		assertTrue(bufferedImagesEqual(oneByOne(BLACK_TRANSPARENT), ImageUtil.fillImage(oneByOne(BLACK_TRANSPARENT), WHITE)));

		// fillImage(BufferedImage image, Color color, Predicate<Color> fillCondition)
		BufferedImage expected = solidColor(CORNER_SIZE, CORNER_SIZE, WHITE);
		expected.setRGB(0, 0, new Color(0, true).getRGB());
		assertTrue(bufferedImagesEqual(expected, ImageUtil.fillImage(BLACK_PIXEL_TOP_LEFT, WHITE, ColorUtil::isFullyTransparent)));
	}