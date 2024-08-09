public static String get(Pattern pattern, String content, int groupIndex) {
		if(null == content || null == pattern){
			return null;
		}
		
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			return matcher.group(groupIndex);
		}
		return null;
	}