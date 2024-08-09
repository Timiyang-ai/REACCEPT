public ThumbnailParameter build()
	{
		ThumbnailParameter param = new ThumbnailParameter(
				new Dimension(width, height),
				watermarks,
				keepAspectRatio,
				thumbnailFormat,
				thumbnailQuality,
				imageType
		);
			
		return param;
	}