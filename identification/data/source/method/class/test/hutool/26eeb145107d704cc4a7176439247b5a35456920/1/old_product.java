public static String normalize(String path) {
		return path.replaceAll("[/\\\\]{1,}", "/");
	}