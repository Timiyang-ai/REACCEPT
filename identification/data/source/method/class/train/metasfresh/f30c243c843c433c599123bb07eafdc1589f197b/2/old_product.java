public static String rpadZero(final String value, final int size, final String description)
	{
		final String valueFixed;
		if (value == null)
		{
			valueFixed = "";
		}
		else
		{
			valueFixed = value.trim();
		}
		if (valueFixed.length() > size)
		{
			throw new AdempiereException("value='" + valueFixed + "' of '" + description + "' is bigger than " + size + " characters");
		}
		final String s = valueFixed + "0000000000000000000";
		return s.substring(0, size);
	}