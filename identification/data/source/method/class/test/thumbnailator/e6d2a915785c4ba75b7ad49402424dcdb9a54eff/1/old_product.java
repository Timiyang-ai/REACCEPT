public static void createThumbnail(
			InputStream is,
			OutputStream os,
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
					true,
					ThumbnailParameter.ORIGINAL_FORMAT,
					ThumbnailParameter.DEFAULT_FORMAT_TYPE,
					ThumbnailParameter.DEFAULT_QUALITY,
					BufferedImage.TYPE_INT_ARGB,
					null,
					Resizers.PROGRESSIVE
			);
		
		Thumbnailator.createThumbnail(new StreamThumbnailTask(param, is, os));
	}