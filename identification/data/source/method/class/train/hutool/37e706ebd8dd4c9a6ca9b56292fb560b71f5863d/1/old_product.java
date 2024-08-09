public static String toParams(Map<String, Object> paramMap) {
		return CollectionUtil.join(paramMap.entrySet(), "&");
	}