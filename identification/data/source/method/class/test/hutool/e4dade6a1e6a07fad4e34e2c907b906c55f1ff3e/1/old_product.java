public final static void cut(InputStream srcStream, OutputStream destStream, int x, int y, int width, int height) {
		try {
			cut(ImageIO.read(srcStream), ImageIO.createImageOutputStream(destStream), x, y, width, height);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}