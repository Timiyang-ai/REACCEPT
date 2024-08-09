public static String colorToHexCode(final Color color)
	{
		return String.format("%06x", color.getRGB() & 0xFFFFFF);
	}