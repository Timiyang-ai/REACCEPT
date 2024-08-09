public boolean isCached(T cacheData)
	{
		String ret = m_cache.put(cacheData, "");

		return (ret != null);
	}