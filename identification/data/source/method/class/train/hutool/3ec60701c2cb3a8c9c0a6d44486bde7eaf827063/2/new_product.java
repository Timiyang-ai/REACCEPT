public static String getAbsolutePath(String path) {
		if (path == null) {
			path = StrUtil.EMPTY;
		} else {
			path = normalize(path);

			if (StrUtil.C_SLASH == path.charAt(0) || path.matches("^[a-zA-Z]:/.*")) {
				// 给定的路径已经是绝对路径了
				return path;
			}
		}
		
		//兼容Spring风格的ClassPath路径，去除前缀，不区分大小写
		path = StrUtil.removePrefixIgnoreCase(path, "classpath:");
		path = StrUtil.removePrefix(path, StrUtil.SLASH);

		// 相对于ClassPath路径
		ClassLoader classLoader = ClassUtil.getClassLoader();
		URL url = classLoader.getResource(path);
		String reultPath = url != null ? url.getPath() : ClassUtil.getClassPath() + path;
		// return StrUtil.removePrefix(reultPath, PATH_FILE_PRE);
		return reultPath;
	}