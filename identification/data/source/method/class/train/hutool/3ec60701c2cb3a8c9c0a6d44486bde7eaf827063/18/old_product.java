public static String getAbsolutePath(String path, Class<?> baseClass) {
		if (path == null) {
			path = StrUtil.EMPTY;
		}
		if (baseClass == null) {
			return getAbsolutePath(path);
		}
		// return baseClass.getResource(path).getPath();
		return StrUtil.removePrefix(PATH_FILE_PRE, baseClass.getResource(path).getPath());
	}