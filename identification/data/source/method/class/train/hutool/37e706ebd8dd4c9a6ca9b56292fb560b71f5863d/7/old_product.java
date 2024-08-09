public static String toParams(Map<String, Object> paramMap, Charset charset) {
		if(CollectionUtil.isEmpty(paramMap)){
			return StrUtil.EMPTY;
		}
		if(null == charset){//默认编码为系统编码
			charset = CharsetUtil.CHARSET_UTF_8;
		}
		
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		String key;
		String value;
		for (Entry<String, Object> item : paramMap.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append("&");
			}
//			sb.append(encode(item.getKey(), charset)).append("=").append(encode(Convert.toStr(item.getValue()), charset));
			key = item.getKey();
			value = encode(Convert.toStr(item.getValue()), charset);
			if(StrUtil.isNotEmpty(key)){
				sb.append(key).append("=");
				if(StrUtil.isNotEmpty(value)){
					sb.append(value);
				}
			}
		}
		return sb.toString();
	}