public void clear()
	{
		// Clear
		cache.invalidateAll();
		cache.cleanUp();

		m_justReset = true;
	}