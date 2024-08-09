public final static void cut(Image srcImage, ImageOutputStream destImageStream, Rectangle rectangle) {
		final BufferedImage tag = cut(srcImage, rectangle);
		writeJpg(tag, destImageStream);
	}