public static String toParams(Map<String, Object> paramMap) {
		if(CollectionUtil.isEmpty(paramMap)){
			return StrUtil.EMPTY;
		}
		return CollectionUtil.join(paramMap.entrySet(), "&");
	}