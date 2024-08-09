public ThumbnailParameter build()
	{
		if (!Double.isNaN(scalingFactor))
		{
			// If scaling factor has been set.
			return new ThumbnailParameter(
					scalingFactor,
					keepAspectRatio,
					thumbnailFormat,
					thumbnailFormatType,
					thumbnailQuality,
					imageType,
					filters,
					resizer
			);
			
		}
		else if (width != UNINITIALIZED && height != UNINITIALIZED)
		{
			return new ThumbnailParameter(
					new Dimension(width, height),
					keepAspectRatio,
					thumbnailFormat,
					thumbnailFormatType,
					thumbnailQuality,
					imageType,
					filters,
					resizer
			);
		}
		else
		{
			throw new IllegalStateException(
					"The size nor the scaling factor has been set."
			);
		}
	}