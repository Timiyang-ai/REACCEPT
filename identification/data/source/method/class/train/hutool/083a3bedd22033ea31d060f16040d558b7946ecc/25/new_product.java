public static void scale(File srcImageFile, File destImageFile, float scale) {
		ImageOutputStream imageOutputStream = null;
		try {
			imageOutputStream = ImageIO.createImageOutputStream(destImageFile);
			scale(ImageIO.read(srcImageFile), imageOutputStream, scale);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		} finally {
			IoUtil.close(imageOutputStream);
		}
	}