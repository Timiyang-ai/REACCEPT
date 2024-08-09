public static BufferedImage cut(Image srcImage, int x, int y, int width, int height) {
		ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(srcImage.getSource(), cropFilter));
		
		final BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		draw(result, img, new Rectangle(0, 0, width, height));
		return result;
	}