public void resolveRelative(final Url relative)
	{
		if (getSegments().size() > 0)
		{
			// strip the first non-folder segment (if it is not empty)
			getSegments().remove(getSegments().size() - 1);
		}

		// remove leading './' (current folder) and empty segments, process any ../ segments from the
		// relative url
		while (!relative.getSegments().isEmpty())
		{
			if (".".equals(relative.getSegments().get(0)))
			{
				relative.getSegments().remove(0);
			}
			else if ("".equals(relative.getSegments().get(0)))
			{
				relative.getSegments().remove(0);
			}
			else if ("..".equals(relative.getSegments().get(0)))
			{
				relative.getSegments().remove(0);
				if (getSegments().isEmpty() == false)
				{
					getSegments().remove(getSegments().size() - 1);
				}
			}
			else
			{
				break;
			}
		}

		if (!getSegments().isEmpty() && relative.getSegments().isEmpty())
		{
			getSegments().add("");
		}

		// append the remaining relative segments
		getSegments().addAll(relative.getSegments());

		// replace query params with the ones from relative
		parameters.clear();
		parameters.addAll(relative.getQueryParameters());
	}