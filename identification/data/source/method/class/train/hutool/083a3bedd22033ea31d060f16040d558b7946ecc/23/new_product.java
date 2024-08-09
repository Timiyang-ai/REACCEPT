public static void scale(ImageInputStream srcStream, ImageOutputStream destStream, float scale) {
		scale(read(srcStream), destStream, scale);
	}