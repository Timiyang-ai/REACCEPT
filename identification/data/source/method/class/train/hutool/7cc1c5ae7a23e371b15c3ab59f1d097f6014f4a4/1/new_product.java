public static void rotate(Image image, int degree, File outFile) throws IORuntimeException {
		write(rotate(image, degree), outFile);
	}