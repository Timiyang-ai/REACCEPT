public final static void cut(Image srcImage, ImageOutputStream destImageStream, Rectangle rectangle) throws IORuntimeException{
		writeJpg(cut(srcImage, rectangle), destImageStream);
	}