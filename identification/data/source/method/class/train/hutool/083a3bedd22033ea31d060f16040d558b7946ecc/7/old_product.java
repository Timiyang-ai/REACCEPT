public final static void scale(InputStream srcStream, OutputStream destStream, int height, int width, Color fixedColor) {
		try {
			scale(ImageIO.read(srcStream), ImageIO.createImageOutputStream(destStream), height, width, fixedColor);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}