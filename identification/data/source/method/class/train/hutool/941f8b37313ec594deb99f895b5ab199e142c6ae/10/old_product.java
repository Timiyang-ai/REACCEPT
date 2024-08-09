public static String subPath(String dirPath, String filePath) {
		if (StrUtil.isNotEmpty(dirPath) && StrUtil.isNotEmpty(filePath)) {
			dirPath = normalize(dirPath);
			filePath = normalize(filePath);

			if (filePath != null && filePath.toLowerCase().startsWith(dirPath.toLowerCase())) {
				if (false == filePath.equals(dirPath)) {
					filePath = filePath.substring(dirPath.length() + 1);
				} else {
					filePath = StrUtil.EMPTY;
				}
			}
		}
		return filePath;
	}