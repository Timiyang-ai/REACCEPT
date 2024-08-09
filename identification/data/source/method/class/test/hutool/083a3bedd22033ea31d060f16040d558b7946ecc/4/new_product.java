public final static void scale(File srcImageFile, File destImageFile, int width, int height, Color fixedColor) {
		ImageOutputStream imageOutputStream = null;
		try {
			imageOutputStream = ImageIO.createImageOutputStream(destImageFile);
			scale(ImageIO.read(srcImageFile), imageOutputStream, width, height, fixedColor);
		} catch (IOException e) {
			throw new UtilException(e);
		} finally {
			IoUtil.close(imageOutputStream);
		}
	}