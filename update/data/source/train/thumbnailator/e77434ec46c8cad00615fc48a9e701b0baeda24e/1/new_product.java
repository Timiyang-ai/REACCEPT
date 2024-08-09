public static void createThumbnail(
			InputStream is,
			OutputStream os,
			String format,
			int width,
			int height
	) throws IOException
	{
		validateDimensions(width, height);
		
		if (is == null)
		{
			throw new NullPointerException("InputStream is null.");
		} 
		else if (os == null)
		{
			throw new NullPointerException("OutputStream is null.");
		}

		Thumbnails.of(is)
			.size(width, height)
			.outputFormat(format)
			.toOutputStream(os);
	}