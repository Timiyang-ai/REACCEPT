public final static void scale(ImageInputStream srcStream, ImageOutputStream destStream, int height, int width, Color fixedColor) {
		try {
			scale(ImageIO.read(srcStream), destStream, height, width, fixedColor);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}