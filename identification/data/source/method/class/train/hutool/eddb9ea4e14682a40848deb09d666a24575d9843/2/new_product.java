public static String delFirst(String regex, String content) {
		if(StrUtil.hasBlank(regex, content)){
			return content;
		}
		
//		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
		return delFirst(pattern, content);
	}