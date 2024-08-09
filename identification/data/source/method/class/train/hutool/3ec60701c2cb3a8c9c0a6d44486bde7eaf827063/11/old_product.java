public static boolean del(String fullFileOrDirPath) throws IOException {
		return del(file(fullFileOrDirPath));
	}