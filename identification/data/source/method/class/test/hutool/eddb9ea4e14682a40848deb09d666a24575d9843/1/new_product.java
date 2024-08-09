public static String extractMulti(String regex, String content, String template) {
		if(null == content || null == regex || null == template){
			return null;
		}
		
//		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
		return extractMulti(pattern, content, template);
	}