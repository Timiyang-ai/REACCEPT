public static void scale(Image srcImg, ImageOutputStream destImageStream, float scale) {
		final Image image = scale(srcImg, scale);
		writeJpg(image, destImageStream);
	}