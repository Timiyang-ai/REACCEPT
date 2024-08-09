public static String toParams(Map<String, ?> paramMap, Charset charset) {
		if(CollectionUtil.isEmpty(paramMap)){
			return StrUtil.EMPTY;
		}
		if(null == charset){//默认编码为系统编码
			charset = CharsetUtil.CHARSET_UTF_8;
		}
		
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		String key;
		Object value;
		String valueStr;
		for (Entry<String, ?> item : paramMap.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append("&");
			}
			key = item.getKey();
			value = item.getValue();
			if(value instanceof Iterable){
				value = CollectionUtil.join((Iterable<?>)value, ",");
			}else if(value instanceof Iterator){
				value = CollectionUtil.join((Iterator<?>)value, ",");
			}
			valueStr = Convert.toStr(value);
			if(StrUtil.isNotEmpty(key)){
				sb.append(encode(key, charset)).append("=");
				if(StrUtil.isNotEmpty(valueStr)){
					sb.append(encode(valueStr, charset));
				}
			}
		}
		return sb.toString();
	}