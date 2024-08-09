public final static BufferedImage cut(Image srcImage, int x, int y, int width, int height) {
//		final Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
		ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(srcImage.getSource(), cropFilter));
		
		final BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = result.getGraphics();
		g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
		g.dispose();
		
		return result;
	}