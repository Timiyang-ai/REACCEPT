public static String subPath(String rootDir, File file) {
		if (StrUtil.isEmpty(rootDir)) {
		}

		String subPath = null;
		try {
			subPath = file.getCanonicalPath();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}

		if (StrUtil.isNotEmpty(rootDir) && StrUtil.isNotEmpty(subPath)) {
			rootDir = normalize(rootDir);
			subPath = normalize(subPath);

			if (subPath != null && subPath.toLowerCase().startsWith(subPath.toLowerCase())) {
				subPath = subPath.substring(rootDir.length() + 1);
			}
		}
		return subPath;
	}