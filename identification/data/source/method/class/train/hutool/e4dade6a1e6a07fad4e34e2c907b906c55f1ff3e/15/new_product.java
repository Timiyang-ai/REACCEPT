public final static void cut(Image srcImage, ImageOutputStream destImageStream, int x, int y, int width, int height) {
		final BufferedImage tag = cut(srcImage, x, y, width, height);
		writeJpg(tag, destImageStream);
	}