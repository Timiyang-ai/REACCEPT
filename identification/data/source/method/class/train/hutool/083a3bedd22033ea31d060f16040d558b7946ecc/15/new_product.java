public final static void scale(Image srcImage, ImageOutputStream destImageStream, int height, int width, Color fixedColor) {
		final Image image = scale(srcImage, height, width, fixedColor);
		writeJpg(image, destImageStream);
	}