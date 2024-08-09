public static void createThumbnail(
			InputStream is,
			OutputStream os,
			int width,
			int height
	) throws IOException
	{
		Thumbnailator.createThumbnail(
				is, os, ThumbnailParameter.ORIGINAL_FORMAT, width, height);
	}