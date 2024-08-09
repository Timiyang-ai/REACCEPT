public static List<String> split(CharSequence str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
		if(null == str){
			return new ArrayList<>(0);
		}
		return StrSpliter.split(str.toString(), separator, limit, isTrim, ignoreEmpty);
	}