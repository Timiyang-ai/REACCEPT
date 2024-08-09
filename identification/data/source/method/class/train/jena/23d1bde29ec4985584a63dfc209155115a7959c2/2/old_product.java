@Override
	public boolean canRead()
	{
		final CacheKey key = new CacheKey(Action.Read, modelNode);
		Boolean retval = cacheGet(key);
		if (retval == null)
		{
			retval = securityEvaluator.evaluate(Action.Read, modelNode);
			cachePut(key, retval);
		}
		return retval;
	}