public final static void scale(Image srcImg, ImageOutputStream destImageStream, int scale, boolean flag) {
		final Image image = scale(srcImg, scale, flag);
		writeJpg(image, destImageStream);
	}