public static String delFirst(String regex, String content) {
		if(StrUtil.hasBlank(regex, content)){
			return content;
		}
		
		return delFirst(Pattern.compile(regex, Pattern.DOTALL), content);
	}