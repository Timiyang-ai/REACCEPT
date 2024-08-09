public static String lpadZero(final String value, final int size, final String description)
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
		final String s = "0000000000000000000" + valueFixed;
		return s.substring(s.length() - size);
	}