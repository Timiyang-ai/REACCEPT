public static String toParams(Map<String, Object> paramMap, String charset) {
		if(CollectionUtil.isEmpty(paramMap)){
			return StrUtil.EMPTY;
		}
		
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (Entry<String, Object> item : paramMap.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append("&");
			}
			sb.append(encode(item.getKey(), charset)).append("=").append(encode(Conver.toStr(item.getValue()), charset));
		}
		return sb.toString();
	}