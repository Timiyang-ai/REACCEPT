public static String rpadZero(final String value, final int size, final String description)
	{
		final String valueFixed = prepareValueForPadding(value, size, description);

		final String s = valueFixed + "0000000000000000000";
		return s.substring(0, size);
	}