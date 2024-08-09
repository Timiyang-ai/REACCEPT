public static String delAll(String regex, String content) {
		if(StrUtil.hasBlank(regex, content)){
			return content;
		}
		
		return delAll(Pattern.compile(regex, Pattern.DOTALL), content);
	}