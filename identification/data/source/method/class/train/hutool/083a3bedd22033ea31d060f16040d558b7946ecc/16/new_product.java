public static void scale(ImageInputStream srcStream, ImageOutputStream destStream, float scale) {
		try {
			scale(ImageIO.read(srcStream), destStream, scale);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}