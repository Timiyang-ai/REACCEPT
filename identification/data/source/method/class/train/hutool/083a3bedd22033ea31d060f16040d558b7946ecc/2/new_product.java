public static void scale(InputStream srcStream, OutputStream destStream, float scale) {
		try {
			scale(ImageIO.read(srcStream), ImageIO.createImageOutputStream(destStream), scale);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}