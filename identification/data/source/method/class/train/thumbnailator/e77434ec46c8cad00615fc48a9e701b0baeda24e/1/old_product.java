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
		
		ThumbnailParameter param = 
			new ThumbnailParameter(
					new Dimension(width, height),
					null,
					true,
					format,
					ThumbnailParameter.DEFAULT_FORMAT_TYPE,
					ThumbnailParameter.DEFAULT_QUALITY,
					ThumbnailParameter.DEFAULT_IMAGE_TYPE,
					null,
					DefaultResizerFactory.getInstance(),
					true,
					true
			);
		
		Thumbnailator.createThumbnail(new StreamThumbnailTask(param, is, os));
	}