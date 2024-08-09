public static void scale(Image srcImg, ImageOutputStream destImageStream, float scale) throws IORuntimeException{
		writeJpg(scale(srcImg, scale), destImageStream);
	}