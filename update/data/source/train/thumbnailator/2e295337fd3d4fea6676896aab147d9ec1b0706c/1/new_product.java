public ThumbnailParameter build()
	{
		if (!Double.isNaN(widthScalingFactor))
		{
			// If scaling factor has been set.
			return new ThumbnailParameter(
					widthScalingFactor,
					heightScalingFactor,
					sourceRegion,
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
					sourceRegion,
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