public final static void cut(ImageInputStream srcStream, ImageOutputStream destStream, int x, int y, int width, int height) {
		try {
			cut(ImageIO.read(srcStream), destStream, x, y, width, height);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}