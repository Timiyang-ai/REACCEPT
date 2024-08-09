public String toString(final Locale locale)
	{
		if (terabytes() >= 1.0)
		{
			return unitString(terabytes(), "T", locale);
		}

		if (gigabytes() >= 1.0)
		{
			return unitString(gigabytes(), "G", locale);
		}

		if (megabytes() >= 1.0)
		{
			return unitString(megabytes(), "M", locale);
		}

		if (kilobytes() >= 1.0)
		{
			return unitString(kilobytes(), "K", locale);
		}

		return Long.toString(value) + " bytes";
	}