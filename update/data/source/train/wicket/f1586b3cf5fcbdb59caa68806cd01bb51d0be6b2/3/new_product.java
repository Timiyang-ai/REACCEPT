@Override
	public boolean accept(String path)
	{
		// First check the cache
		if (cache != null)
		{
			Boolean rtn = cache.get(path);
			if (rtn != null)
			{
				return rtn;
			}
		}

		// Check typical files such as log4j.xml etc.
		if (super.accept(path) == false)
		{
			return false;
		}

		// Check against the pattern
		boolean hit = false;
		for (SearchPattern pattern : new ReverseListIterator<>(this.pattern))
		{
			if ((pattern != null) && pattern.isActive())
			{
				if (pattern.matches(path))
				{
					hit = pattern.isInclude();
					break;
				}
			}
		}

		if (cache != null)
		{
			// Do not use putIfAbsent(). See newCache()
			cache.put(path, (hit ? Boolean.TRUE : Boolean.FALSE));
		}

		if (hit == false)
		{
			log.warn("Access denied to shared (static) resource: " + path);
		}

		return hit;
	}