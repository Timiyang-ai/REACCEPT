public static String escape(String content) {
		if(StrUtil.isBlank(content)){
			return content;
		}
		
		final StringBuilder builder = new StringBuilder();
		char current;
		for(int i = 0; i < content.length(); i++) {
			current = content.charAt(i);
			if(RE_KEYS.contains(current)) {
				builder.append('\\');
			}
			builder.append(current);
		}
		return builder.toString();
	}