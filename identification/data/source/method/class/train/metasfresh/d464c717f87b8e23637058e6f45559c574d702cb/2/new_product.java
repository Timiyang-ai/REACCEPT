public V get(final K key, final Supplier<V> valueInitializer)
	{
		if (valueInitializer == null)
		{
			return cache.getIfPresent(key);
		}

		return get(key, new Callable<V>(){
			@Override
			public String toString()
			{
				return "Callable[" + valueInitializer + "]";
			}

			@Override
			public V call() throws Exception
			{
				return valueInitializer.get();
			}});
	}