public static BufferedImage resizeImage(final BufferedImage image, final int newWidth, final int newHeight)
	{
		final Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		final BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

		final Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return dimg;
	}