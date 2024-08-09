	@Test
	public void bufferedImageFromImage()
	{
		final BufferedImage buffered = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		assertEquals(buffered, ImageUtil.bufferedImageFromImage(buffered));
	}