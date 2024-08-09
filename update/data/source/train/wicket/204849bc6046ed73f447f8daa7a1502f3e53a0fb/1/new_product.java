@Override
	public int getCompatibilityScore(Request request)
	{
		if (urlStartsWith(request.getUrl(), mountSegments))
		{
			/* see WICKET-5056 - alter score with pathSegment type */
			int countOptional = 0;
			int fixedSegments = 0;
			for (MountPathSegment pathSegment : pathSegments)
			{
				fixedSegments += pathSegment.getFixedPartSize();
				countOptional += pathSegment.getOptionalParameters();
			}
			return mountSegments.length - countOptional + fixedSegments;
		}
		else
		{
			return 0;
		}
	}