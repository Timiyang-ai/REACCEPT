public final static void scale(InputStream srcStream, OutputStream destStream, int scale, boolean flag) {
		try {
			scale(ImageIO.read(srcStream), ImageIO.createImageOutputStream(destStream), scale, flag);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}