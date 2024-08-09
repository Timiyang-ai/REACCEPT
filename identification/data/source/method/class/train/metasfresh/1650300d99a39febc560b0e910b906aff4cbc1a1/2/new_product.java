public void putAll(final Map<? extends K, ? extends V> map)
	{
		cache.putAll(map);

		for (final Entry<? extends K, ? extends V> entry : map.entrySet())
		{
			fireAdditionListener(entry.getKey(), entry.getValue());
		}
	}