public static BufferedImage resizeImage(final BufferedImage image, final int newWidth, final int newHeight)
	{
		final Image resized = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		return ImageUtil.bufferedImageFromImage(resized);
	}