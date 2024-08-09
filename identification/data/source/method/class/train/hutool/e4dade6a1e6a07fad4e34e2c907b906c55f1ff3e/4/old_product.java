public final static void cut(InputStream srcStream, OutputStream destStream, Rectangle rectangle) {
		try {
			cut(ImageIO.read(srcStream), ImageIO.createImageOutputStream(destStream), rectangle);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}