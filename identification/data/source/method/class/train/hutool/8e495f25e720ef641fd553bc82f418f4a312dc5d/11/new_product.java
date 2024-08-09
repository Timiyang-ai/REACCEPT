public static String[] split(CharSequence str, CharSequence separator) {
		if (str == null) {
			return new String[]{};
		}
		
		final String separatorStr = (null == separator) ? null : separator.toString();
		return StrSpliter.splitToArray(str.toString(), separatorStr, 0, false, false);
	}