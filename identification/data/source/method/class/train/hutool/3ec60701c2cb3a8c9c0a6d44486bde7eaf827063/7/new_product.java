public static String getAbsolutePath(String path, Class<?> baseClass) {
		String normalPath;
		if (path == null) {
			normalPath = StrUtil.EMPTY;
		} else {
			normalPath = normalize(path);
			if (isAbsolutePath(normalPath)) {
				// 给定的路径已经是绝对路径了
				return normalPath;
			}
		}

		// 相对于ClassPath路径
		final URL url = ResourceUtil.getResource(normalPath, baseClass);
		if (null != url) {
			//对于jar中文件包含file:前缀，需要去掉此类前缀，在此做标准化，since 3.0.8 解决中文或空格路径被编码的问题
			return FileUtil.normalize(URLUtil.getDecodedPath(url));
		}
		
		// 如果资源不存在，则返回一个拼接的资源绝对路径
		final String classPath = ClassUtil.getClassPath();
		if (null == classPath) {
			throw new NullPointerException("ClassPath is null !");
		}
		
		//资源不存在的情况下使用标准化路径有问题，使用原始路径拼接后标准化路径
		return normalize(classPath.concat(path));
	}