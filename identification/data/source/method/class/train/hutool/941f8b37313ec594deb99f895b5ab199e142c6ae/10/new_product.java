public static String subPath(String dirPath, String filePath) {
		if (StrUtil.isNotEmpty(dirPath) && StrUtil.isNotEmpty(filePath)) {
			dirPath = StrUtil.addSuffixIfNot(normalize(dirPath), "/");
			filePath = StrUtil.removeSuffix(normalize(filePath), "/");
			
			return StrUtil.removePrefixIgnoreCase(filePath, dirPath);
		}
		return filePath;
	}