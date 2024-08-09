public final static void scale(File srcImageFile, File destImageFile, int width, int height, Color fixedColor) {
		try {
			scale(ImageIO.read(srcImageFile), ImageIO.createImageOutputStream(destImageFile), width, height, fixedColor);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}