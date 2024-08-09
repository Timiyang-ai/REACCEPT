public static String delFirst(String regex, String content) {
		if(null == content || null == regex){
			return content;
		}
		
		return content.replaceFirst(regex, "");
	}