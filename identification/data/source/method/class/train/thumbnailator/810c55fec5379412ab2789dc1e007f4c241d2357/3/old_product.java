public ThumbnailParameter build()
	{
		ThumbnailParameter param = new ThumbnailParameter(
				new Dimension(width, height),
				keepAspectRatio,
				thumbnailFormat,
				thumbnailFormatType,
				thumbnailQuality,
				imageType,
				filters,
				resizer
		);
			
		return param;
	}