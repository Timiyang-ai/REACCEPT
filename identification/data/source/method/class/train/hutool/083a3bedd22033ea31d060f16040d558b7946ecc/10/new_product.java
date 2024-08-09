public final static void scale(File srcImageFile, File destImageFile, int width, int height, Color fixedColor) throws IORuntimeException{
		write(read(srcImageFile), destImageFile);
	}