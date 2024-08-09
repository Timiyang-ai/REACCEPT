public static void scale(File srcImageFile, File destImageFile, float scale) {
		try {
			scale(ImageIO.read(srcImageFile), ImageIO.createImageOutputStream(destImageFile), scale);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}