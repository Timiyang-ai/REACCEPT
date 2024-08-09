public static BufferedImage bufferedImageFromImage(final Image image)
	{
		if (image instanceof BufferedImage)
		{
			return (BufferedImage) image;
		}

		final BufferedImage out = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g2d = out.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return out;
	}