public static BufferedImage fillImage(final BufferedImage image, final Color color)
	{
		return fillImage(image, color, ColorUtil::isNotFullyTransparent);
	}