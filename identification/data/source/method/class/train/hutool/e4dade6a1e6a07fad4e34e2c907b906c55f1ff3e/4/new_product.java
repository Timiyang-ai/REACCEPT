public final static void cut(InputStream srcStream, OutputStream destStream, Rectangle rectangle) {
		cut(read(srcStream), destStream, rectangle);
	}