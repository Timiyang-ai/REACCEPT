	@Test
	public void resizeImage()
	{
		// TODO: test image contents after changing size

		final BufferedImage larger = ImageUtil.resizeImage(oneByOne(BLACK), 46, 46);
		final BufferedImage smaller = ImageUtil.resizeImage(centeredPixel(WHITE), 1, 1);
		final BufferedImage stretched = ImageUtil.resizeImage(solidColor(30, 30, RED), 12, 34);

		assertEquals(46, larger.getWidth());
		assertEquals(46, larger.getHeight());
		assertEquals(1, smaller.getWidth());
		assertEquals(1, smaller.getHeight());
		assertEquals(12, stretched.getWidth());
		assertEquals(34, stretched.getHeight());

		final BufferedImage[] assertSameAfterResize = new BufferedImage[] {
			oneByOne(WHITE),
			oneByOne(GRAY),
			oneByOne(BLACK),
			oneByOne(RED),
			oneByOne(GREEN),
			oneByOne(BLUE),
			oneByOne(BLACK_HALF_TRANSPARENT),
			oneByOne(BLACK_TRANSPARENT),
			centeredPixel(WHITE),
			centeredPixel(GRAY),
			centeredPixel(BLACK),
			BLACK_PIXEL_TOP_LEFT,
			BLACK_PIXEL_TOP_RIGHT,
			BLACK_PIXEL_BOTTOM_LEFT,
			BLACK_PIXEL_BOTTOM_RIGHT,
		};
		for (BufferedImage image : assertSameAfterResize)
		{
			assertTrue(bufferedImagesEqual(image, ImageUtil.resizeImage(image, image.getWidth(), image.getHeight())));
		}
	}