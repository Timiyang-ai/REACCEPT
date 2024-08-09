public final static void scale(InputStream srcStream, OutputStream destStream, int width, int height, Color fixedColor) throws IORuntimeException{
		scale(read(srcStream), getImageOutputStream(destStream), width, height, fixedColor);
	}