public final static void scale(File srcImageFile, File destImageFile, int scale, boolean flag) {
		try {
			scale(ImageIO.read(srcImageFile), ImageIO.createImageOutputStream(destImageFile), scale, flag);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}