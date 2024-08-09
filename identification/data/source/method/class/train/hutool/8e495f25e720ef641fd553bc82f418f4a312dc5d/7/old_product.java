public static List<String> split(CharSequence str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
		return StrSpliter.split(str.toString(), separator, limit, isTrim, ignoreEmpty);
	}