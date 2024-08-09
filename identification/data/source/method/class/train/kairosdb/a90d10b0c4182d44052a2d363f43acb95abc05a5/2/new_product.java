public boolean isCached(T cacheData)
	{
		String ret;

		synchronized (m_lock)
		{
			ret = m_cache.put(cacheData, "");
		}

		return (ret != null);
	}