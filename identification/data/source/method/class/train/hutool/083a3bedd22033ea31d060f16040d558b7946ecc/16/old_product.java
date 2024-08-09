public final static void scale(ImageInputStream srcStream, ImageOutputStream destStream, int scale, boolean flag) {
		try {
			scale(ImageIO.read(srcStream), destStream, scale, flag);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}