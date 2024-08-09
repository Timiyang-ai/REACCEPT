public V get(final K key, final Callable<V> valueInitializer)
	{
		if (valueInitializer == null)
		{
			return cache.getIfPresent(key);
		}

		try
		{
			return cache.get(key, valueInitializer);
		}
		catch (final InvalidCacheLoadException e)
		{
			// Exception thrown when the Callable returns null
			// We can safely ignore it and return null.
			// The value was not cached.
			return null;
		}
		catch (final ExecutionException e)
		{
			throw AdempiereException.wrapIfNeeded(e);
		}
		catch (final UncheckedExecutionException e)
		{
			throw (RuntimeException)e.getCause();
		}
		catch (final ExecutionError e)
		{
			throw (Error)e.getCause();
		}
	}