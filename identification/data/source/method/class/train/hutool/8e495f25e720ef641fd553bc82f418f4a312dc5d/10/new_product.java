public static String[] split(CharSequence str, int len) {
		if(null == str){
			return new String[]{};
		}
		return StrSpliter.splitByLength(str.toString(), len);
	}