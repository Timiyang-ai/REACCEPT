public static String lpadZero(final String value, final int size, final String description)
	{
		final String valueFixed = prepareValueForPadding(value, size, description);

		final String s = "0000000000000000000" + valueFixed;
		return s.substring(s.length() - size);
	}