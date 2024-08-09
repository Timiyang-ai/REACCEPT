public final static void cut(File srcImgFile, File destImgFile, int x, int y, int width, int height) {
		try {
			cut(ImageIO.read(srcImgFile), ImageIO.createImageOutputStream(destImgFile), x, y, width, height);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}