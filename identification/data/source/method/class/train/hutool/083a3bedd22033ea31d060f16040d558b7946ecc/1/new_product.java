public final static void scale(ImageInputStream srcStream, ImageOutputStream destStream, int width, int height, Color fixedColor) {
		try {
			scale(ImageIO.read(srcStream), destStream, width, height, fixedColor);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}