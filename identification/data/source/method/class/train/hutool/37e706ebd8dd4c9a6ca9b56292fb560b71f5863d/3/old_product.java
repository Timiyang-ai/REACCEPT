public static String toParams(Map<String, Object> paramMap, Charset charset) {
		if(CollectionUtil.isEmpty(paramMap)){
			return StrUtil.EMPTY;
		}
		if(null == charset){//默认编码为系统编码
			charset = CharsetUtil.CHARSET_UTF_8;
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