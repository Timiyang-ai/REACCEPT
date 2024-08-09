public static BufferedImage bufferedImageFromImage(final Image image)
	{
		if (image instanceof BufferedImage)
		{
			return (BufferedImage) image;
		}

		return toARGB(image);
	}