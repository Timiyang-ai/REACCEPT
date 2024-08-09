public final static void scale(InputStream srcStream, OutputStream destStream, int width, int height, Color fixedColor) {
		try {
			scale(ImageIO.read(srcStream), ImageIO.createImageOutputStream(destStream), width, height, fixedColor);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}