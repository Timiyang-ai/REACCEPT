public static String escape(String content) {
		if(StrUtil.isBlank(content)){
			return content;
		}
		
		final StringBuilder builder = new StringBuilder();
		int len = content.length();
		char current;
		for(int i = 0; i < len; i++) {
			current = content.charAt(i);
			if(RE_KEYS.contains(current)) {
				builder.append('\\');
			}
			builder.append(current);
		}
		return builder.toString();
	}