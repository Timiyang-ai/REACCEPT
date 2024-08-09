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

		// 相对于ClassPath路径
		final URL url = ResourceUtil.getResource(path, baseClass);
		if (null != url) {
			// since 3.0.8 解决中文或空格路径被编码的问题
			return FileUtil.normalize(URLUtil.getDecodedPath(url));
		}
		
		// 如果资源不存在，则返回一个拼接的资源绝对路径
		final String classPath = ClassUtil.getClassPath();
		if (null == classPath) {
			throw new NullPointerException("ClassPath is null !");
		}
		return classPath.concat(path);
	}