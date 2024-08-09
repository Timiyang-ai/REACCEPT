public V get(final K key)
	{
		return cache.getIfPresent(key);
	}