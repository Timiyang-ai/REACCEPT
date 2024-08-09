public static String getAbsolutePath(String path, Class<?> baseClass) {
		if (path == null) {
			path = StrUtil.EMPTY;
		} else {
			path = normalize(path);
			if (isAbsolutePath(path)) {
				// 给定的路径已经是绝对路径了
				return path;
			}
		}

		// 兼容Spring风格的ClassPath路径，去除前缀，不区分大小写
		path = StrUtil.removePrefixIgnoreCase(path, "classpath:");
		path = StrUtil.removePrefix(path, StrUtil.SLASH);

		// 相对于ClassPath路径
		final URL url = ClassUtil.getResourceUrl(path, baseClass);
		if (null != url) {
			// since 3.0.8 解决中文或空格路径被编码的问题
			path = URLUtil.getDecodedPath(url);
		} else {
			// 如果资源不存在，则返回一个拼接的资源绝对路径
			final String classPath = ClassUtil.getClassPath();
			if (null == classPath) {
				throw new NullPointerException("ClassPath is null !");
			}
			path = classPath.concat(path);
		}
		return normalize(path);
	}