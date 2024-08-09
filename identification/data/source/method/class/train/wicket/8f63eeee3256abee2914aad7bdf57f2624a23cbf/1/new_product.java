public String toString(final Locale locale)
	{
		if (terabytes() >= 1.0)
		{
			return unitString(terabytes(), "TB", locale);
		}

		if (gigabytes() >= 1.0)
		{
			return unitString(gigabytes(), "GB", locale);
		}

		if (megabytes() >= 1.0)
		{
			return unitString(megabytes(), "MB", locale);
		}

		if (kilobytes() >= 1.0)
		{
			return unitString(kilobytes(), "KB", locale);
		}

		return Long.toString(value) + " bytes";
	}