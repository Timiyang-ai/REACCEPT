public final static void scale(File srcImageFile, File destImageFile, int height, int width, Color fixedColor) {
		try {
			scale(ImageIO.read(srcImageFile), ImageIO.createImageOutputStream(destImageFile), height, width, fixedColor);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}