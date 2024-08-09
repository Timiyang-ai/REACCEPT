public final static void scale(ImageInputStream srcStream, ImageOutputStream destStream, int width, int height, Color fixedColor) throws IORuntimeException{
		scale(read(srcStream), destStream, width, height, fixedColor);
	}