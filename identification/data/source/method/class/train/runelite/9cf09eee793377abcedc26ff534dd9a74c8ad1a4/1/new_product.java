public static String removeTags(String str)
	{
		return TAG_REGEXP.matcher(str).replaceAll("");
	}