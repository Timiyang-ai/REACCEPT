public final static void cut(ImageInputStream srcStream, ImageOutputStream destStream, Rectangle rectangle) {
		cut(read(srcStream), destStream, rectangle);
	}