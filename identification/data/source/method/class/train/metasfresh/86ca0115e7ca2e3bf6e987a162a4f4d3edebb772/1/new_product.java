public static String trunc(
			@Nullable final String str,
			final int length)
	{
		return trunc(str, length, TruncateAt.STRING_END);
	}