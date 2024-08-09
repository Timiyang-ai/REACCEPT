public final static void scale(Image srcImage, ImageOutputStream destImageStream, int width, int height, Color fixedColor) {
		final Image image = scale(srcImage, width, height, fixedColor);
		writeJpg(image, destImageStream);
	}