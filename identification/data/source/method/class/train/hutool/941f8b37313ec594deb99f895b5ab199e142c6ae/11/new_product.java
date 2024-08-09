public static String subPath(String rootDir, File file) {
		try {
			return subPath(rootDir, file.getCanonicalPath());
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}