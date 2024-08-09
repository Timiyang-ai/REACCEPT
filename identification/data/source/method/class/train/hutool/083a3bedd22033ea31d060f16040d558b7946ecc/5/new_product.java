public final static void scale(Image srcImage, ImageOutputStream destImageStream, int width, int height, Color fixedColor) throws IORuntimeException{
		writeJpg(scale(srcImage, width, height, fixedColor), destImageStream);
	}