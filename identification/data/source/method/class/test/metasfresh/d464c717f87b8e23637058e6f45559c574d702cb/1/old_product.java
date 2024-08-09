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
		catch (InvalidCacheLoadException e)
		{
			// Exception thrown when the Callable returns null
			// We can safely ignore it and return null.
			// The value was not cached.
			return null;
		}
		catch (ExecutionException e)
		{
			final Throwable ex = e.getCause();
			throw ex instanceof AdempiereException ? (AdempiereException)ex : new AdempiereException(ex);
		}
		catch (UncheckedExecutionException e)
		{
			throw (RuntimeException)e.getCause();
		}
		catch (ExecutionError e)
		{
			throw (Error)e.getCause();
		}
	}