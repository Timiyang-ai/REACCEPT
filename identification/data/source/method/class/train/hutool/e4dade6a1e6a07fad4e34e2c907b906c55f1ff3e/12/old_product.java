public final static void cut(ImageInputStream srcStream, ImageOutputStream destStream, Rectangle rectangle) {
		try {
			cut(ImageIO.read(srcStream), destStream, rectangle);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}