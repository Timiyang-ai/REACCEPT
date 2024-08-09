public static boolean del(String fullFileOrDirPath) throws IORuntimeException {
		return del(file(fullFileOrDirPath));
	}