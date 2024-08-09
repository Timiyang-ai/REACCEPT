public static String get(String regex, String content, int groupIndex) {
		if(null == content || null == regex){
			return null;
		}
		
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		return get(pattern, content, groupIndex);
	}