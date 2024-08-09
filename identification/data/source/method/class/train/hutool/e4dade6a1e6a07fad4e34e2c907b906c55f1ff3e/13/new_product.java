public final static void cut(File srcImgFile, File destImgFile, Rectangle rectangle) {
		try {
			cut(ImageIO.read(srcImgFile), ImageIO.createImageOutputStream(destImgFile), rectangle);
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}