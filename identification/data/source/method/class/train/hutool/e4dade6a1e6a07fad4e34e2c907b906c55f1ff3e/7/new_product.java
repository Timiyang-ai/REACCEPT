public final static void cut(File srcImgFile, File destImgFile, Rectangle rectangle) {
		ImageOutputStream imageOutputStream = null;
		try {
			imageOutputStream = ImageIO.createImageOutputStream(destImgFile);
			cut(ImageIO.read(srcImgFile), imageOutputStream, rectangle);
		} catch (IOException e) {
			throw new UtilException(e);
		} finally {
			IoUtil.close(imageOutputStream);
		}
	}