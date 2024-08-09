public static boolean isMatch(String regex, String content) {
		if(content == null) {
			//提供null的字符串为不匹配
			return false;
		}
		
		if(StrUtil.isEmpty(regex)) {
			//正则不存在则为全匹配
			return true;
		}
		
//		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
		return isMatch(pattern, content);
	}