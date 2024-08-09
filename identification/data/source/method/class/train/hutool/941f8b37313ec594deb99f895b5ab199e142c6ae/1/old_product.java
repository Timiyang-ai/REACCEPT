public static String subPath(String dirPath, String filePath) {
		if (StrUtil.isEmpty(dirPath)) {
		}

		if (StrUtil.isNotEmpty(dirPath) && StrUtil.isNotEmpty(filePath)) {
			dirPath = normalize(dirPath);
			filePath = normalize(filePath);

			if (filePath != null && filePath.toLowerCase().startsWith(dirPath.toLowerCase())) {
				filePath = filePath.substring(dirPath.length() + 1);
			}
		}
		return filePath;
	}