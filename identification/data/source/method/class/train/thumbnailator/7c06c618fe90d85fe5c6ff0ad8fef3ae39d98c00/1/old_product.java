public static boolean isSupportedOutputFormat(String format)
	{
		for (String supportedFormat : getSupportedOutputFormats())
		{
			if (supportedFormat.equals(format))
			{
				return true;
			}
		}
		
		return false;
	}