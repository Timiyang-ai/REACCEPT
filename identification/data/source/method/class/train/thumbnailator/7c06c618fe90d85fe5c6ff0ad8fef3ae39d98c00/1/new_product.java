public static boolean isSupportedOutputFormat(String format)
	{
		if (format == ThumbnailParameter.ORIGINAL_FORMAT)
		{
			return true;
		}
		
		for (String supportedFormat : getSupportedOutputFormats())
		{
			if (supportedFormat.equals(format))
			{
				return true;
			}
		}
		
		return false;
	}