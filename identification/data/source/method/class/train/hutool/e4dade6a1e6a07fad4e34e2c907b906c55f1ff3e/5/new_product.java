public static BufferedImage cut(Image srcImage, Rectangle rectangle) {
		ImageFilter cropFilter = new CropImageFilter(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(srcImage.getSource(), cropFilter));
		
		final BufferedImage result = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_RGB);
		draw(result, img, new Rectangle(0, 0, rectangle.width, rectangle.height));
		return result;
	}