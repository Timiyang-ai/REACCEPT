public static void rotate(Image image, int degree, File outFile) throws IORuntimeException {
		ImageOutputStream out = null;
		try {
			out = getImageOutputStream(outFile);
			writeJpg(rotate(image, degree), out);
		} finally {
			IoUtil.close(out);
		}
	}