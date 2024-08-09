public static boolean del(String fullFileOrDirPath) throws IOException {
		return del(new File(fullFileOrDirPath));
	}