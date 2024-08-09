public static final String trunc(final String str, final int length)
	{
		if (str == null)
		{
			return str;
		}

		if (str.length() <= length)
		{
			return str;
		}

		return str.substring(0, length);
	}